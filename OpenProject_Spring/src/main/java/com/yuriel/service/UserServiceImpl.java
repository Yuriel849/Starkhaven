package com.yuriel.service;

import java.io.File;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
	
	// 인터페이스 사용하기
	private UserMapper mapper;
 // private UserMapper mapper = template.getMapper(UserMapper.class); -> IMPOSSIBLE! -> DI는 객체 생성 후 실행, 이것은 생성할 떄 실행
	
	@Transactional
	@Override
	public int createUser(UserVO vo, HttpServletRequest request) throws Exception {
		// 같은 이름의 파일을 구분할 수 있도록 사용할 고유번호로 UUID를 얻는다
		UUID uid = UUID.randomUUID();
		
		// 저장될 경로를 계산한다.
		String uploadPath = "/resources/photos/";
		String dir = request.getSession().getServletContext().getRealPath(uploadPath);
		
		// 파일명에 UUID를 더한다.
		String saveFileName = uid.toString() + "_" + vo.getImg().getOriginalFilename();
		
		mapper = template.getMapper(UserMapper.class);
		// users 테이블에 사용자 정보를 추가한다.
		int result = mapper.createUser(vo);
		
		if(!vo.getImg().isEmpty()) { // UserVO 객체의 img 변수가 비어있지 않는다면...
			// 사용자가 업로드한 파일을 지정된 위치로 이동시킨다. 
			vo.getImg().transferTo(new File(dir, saveFileName));
			String imgName = saveFileName;
			// users_attach 테이블에 파일명을 사용자 ID(auto_increment)와 함께 추가한다.
			mapper.addAttach(imgName);
		}
		
		return result;
	}
	
	@Override
	public UserVO login(UserVO vo) throws Exception {
		mapper = template.getMapper(UserMapper.class);
		return mapper.login(vo);
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

}