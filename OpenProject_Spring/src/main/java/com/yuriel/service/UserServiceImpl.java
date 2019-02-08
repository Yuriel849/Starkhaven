package com.yuriel.service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
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
	
//	// servlet-context.xml에 설정한 uploadPath 빈 주입하기
//	@Resource(name = "uploadPath")
//	private String uploadPath;
	
	@Transactional
	@Override
	public int createUser(UserVO vo, HttpServletRequest request) throws Exception {
		// 같은 이름의 파일을 구분할 수 있도록 사용할 고유번호로 UUID를 얻는다
		UUID uid = UUID.randomUUID();
		
		// 저장될 경로를 계산한다.
		String uploadPath = "/upload/";
		String datePath = calcPath(uploadPath);
		String savedPath = uploadPath + datePath;
		// 파일명에 UUID를 더한다.
		String saveFileName = uid.toString() + "_" + vo.getImg().getOriginalFilename();
		
		mapper = template.getMapper(UserMapper.class);
		// users 테이블에 사용자 정보를 추가한다.
		int result = mapper.createUser(vo);
		
		if(!vo.getImg().isEmpty()) { // UserVO 객체의 img 변수가 비어있지 않는다면...
			// 사용자가 업로드한 파일을 지정된 위치로 이동시킨다. 
			vo.getImg().transferTo(new File(savedPath, saveFileName));
			// 파일명에 년/월/일 경로 정보를 붙인다.
			String imgName = datePath + File.separator + saveFileName;
			System.out.println("imgName : " + imgName);
			// users_attach 테이블에 파일명을 사용자 ID(auto_increment)와 함께 추가한다 -> 파일명은 년/월/일 + UUID + 원래 파일이름이다!
			mapper.addAttach(imgName);
		}
		
		return result;
	}
	
	// 년, 월, 일로 세분화할 수 있도록 경로 계산
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);		
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		// 지정된 저장위치에 년/월/일 경로를 만든다 (년/월/일에 해당하는 디렉토리가 있는지 확인하고 없다면 추가한다.)
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	// 년, 월, 일로 세분화할 수 있도록 추가 디렉토리 생성
	private static void makeDir(String uploadPath, String... paths) {
		if(new File(uploadPath + paths[paths.length - 1]).exists()) { return; }
		
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			// dirPath에 해당하는 경로가 존재하지 않는다면 새롭게 디렉토리 생성
			if(!dirPath.exists()) { dirPath.mkdir(); }
		}
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