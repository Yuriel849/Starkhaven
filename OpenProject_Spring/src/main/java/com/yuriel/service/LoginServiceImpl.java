package com.yuriel.service;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.yuriel.domain.UserVO;
import com.yuriel.persistence.UserMapper;

@Service
public class LoginServiceImpl implements LoginService {
	@Inject
	private SqlSessionTemplate template;
	
	// 인터페이스 사용하기
	private UserMapper mapper;
 // private UserMapper mapper = template.getMapper(UserMapper.class); -> IMPOSSIBLE! -> DI는 객체 생성 후 실행, 이것은 생성할 떄 실행
	
//	// servlet-context.xml에 설정한 uploadPath 빈 주입하기
//	@Resource(name = "uploadPath")
//	private String uploadPath;
	
	@Override
	public UserVO login(UserVO vo) throws Exception {
		String uploadPath = "/upload/";

		mapper = template.getMapper(UserMapper.class);
		UserVO result = mapper.login(vo);
		result.setFullName("file:C:/Users/Yuriel/Downloads/Upload/2019/02/08/e6eb8c51-a40b-4451-ad3d-d409ac632894_Sunwapta Falls, Canada.jpg");
		System.out.println("THIS IS THE LOGIN SERVICE IMPL CLASS... THE RESULT OF LOGIN IS : " + result);
		return result;
	}	
}