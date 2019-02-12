package com.yuriel.service;

import java.io.File;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuriel.domain.UserListVO;
import com.yuriel.domain.UserVO;
import com.yuriel.persistence.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Inject
	private SqlSessionTemplate template;
	
	@Inject
	private MimeMailServiceImpl service;
	
	@Inject
	private Sha256 sha256;
	
	@Inject
	private Aes256 aes256;
	
	// 인터페이스 사용하기
	private UserMapper mapper;
 // private UserMapper mapper = template.getMapper(UserMapper.class); -> IMPOSSIBLE! -> DI는 객체 생성 후 실행, 이것은 생성할 떄 실행
	
	@Transactional
	@Override
	public int createUser(UserVO vo, String dir) throws Exception {
		// 같은 이름의 파일을 구분할 수 있도록 사용할 고유 코드를 생성한다.
		String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder rCode = new StringBuilder();
		Random r = new Random();
		for(int i = 0; i < 20; i++) {
			int rand = r.nextInt(62);
			rCode.append(alphabet.charAt(rand));
		}
		System.out.println(rCode);
		String authCode = rCode.toString();
		// 파일명에 UUID를 더한다.
		String saveFileName = authCode + "_" + vo.getImg().getOriginalFilename();
		
		// 회원 이메일, 회원 비밀번호, 회원 이름을 암호화한다 (SHA256 & AES256)
		String email = vo.getEmail();
		vo.setEmail(aes256.encrypt(email));
		String name = vo.getName();
		vo.setName(aes256.encrypt(name));
		String password = vo.getPw();
		vo.setPw(sha256.encrypt(password));
		
		authCode = sha256.encrypt(authCode);
		
		mapper = template.getMapper(UserMapper.class);
		// users 테이블에 사용자 정보를 추가한다.
		int result = mapper.createUser(vo, authCode);
		
		if(!vo.getImg().isEmpty()) { // UserVO 객체의 img 변수가 비어있지 않는다면...
			// 사용자가 업로드한 파일을 지정된 위치로 이동시킨다.
			vo.getImg().transferTo(new File(dir, saveFileName));
			// 업로드한 파일명을 암호화한다.
			saveFileName = aes256.encrypt(saveFileName);
			// users_attach 테이블에 파일명을 사용자 ID(auto_increment)와 함께 추가한다.
			mapper.addAttach(saveFileName);
		}
		
		service.executeJoin(email, authCode);
		
		return result;
	}
	
	@Override
	public UserVO getUser(String email) throws Exception {
		mapper = template.getMapper(UserMapper.class);
		return mapper.selectOne(email);
	}
	
	@Transactional
	@Override
	public UserListVO getUserList(int count, int page) throws Exception {
		mapper = template.getMapper(UserMapper.class);
		
		List<UserVO> list = null;
		int firstRow = 0;
		int countPerPage = count;
		int pageNumber = page;
		int totalCount = mapper.selectCount();
		int pageTotalCount = 0;
		/* Page Total Count:
		 * 	   divides the number of messages by the number of messages per page, then calculates the remainder;
		 *     if the remainder is greater than 0 (cannot be a negative number),
		 *     then another page is required to show the remainder, so +1 to the total number of pages
		 */
		if(totalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = totalCount / countPerPage + (totalCount % countPerPage > 0 ? 1 : 0);
		}
		if(pageNumber <= 0) { pageNumber = 1; }
		else if(pageNumber > pageTotalCount) { pageNumber = pageTotalCount; }
		int currentPageNumber = pageNumber;
		
		if(countPerPage > 0) {
			firstRow = (pageNumber - 1) * countPerPage;
			list = mapper.selectUsers(firstRow, countPerPage);
		} else {
			currentPageNumber = 0;
			list = Collections.emptyList();
		}
		
		UserListVO result = new UserListVO(list, totalCount, currentPageNumber, countPerPage, pageTotalCount, firstRow);
		return result;
	}
	
	public List<UserVO> getAllUsersList() throws Exception {
		mapper = template.getMapper(UserMapper.class);

		// 모든 회원 정보를 가져온다.
		List<UserVO> list = mapper.selectAllUsers();
		
		return list;
	}

	@Transactional
	@Override
	public void deleteUser(String ID) throws Exception {
		mapper = template.getMapper(UserMapper.class);
		mapper.deleteAttach(ID);
		mapper.deleteUser(ID);
	}
	
	@Transactional
	@Override
	public int modifyUser(UserVO vo) throws Exception {
		mapper = template.getMapper(UserMapper.class);
		int result = mapper.modifyUser(vo);
		
		return result;
	}
}