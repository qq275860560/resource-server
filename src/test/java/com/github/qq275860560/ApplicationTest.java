package com.github.qq275860560;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyuanlin@163.com
 *
 */
@SuppressWarnings(value= {"serial" ,"rawtypes"})
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;


 
}

//curl -X POST 'http://localhost:8083/api/github/qq275860560/resource/saveResource?access_token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MDg4NzA3ODYsInVzZXJfbmFtZSI6InVzZXJuYW1lMSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiI5ZDYyNTZhZi01ZDI3LTQ2MGEtYTVlOS1iODkyZTA4NjU4YTciLCJjbGllbnRfaWQiOiJhZG1pbiIsInNjb3BlIjpbIkFETUlOIiwiVVNFUiJdfQ.fG68rdFd33cv1dTsD0eMOV3rZ0QzuW71RYGfKBfUPI9wbKr-4G3fJtmazki21lqn5uFgiYzQ-2NLspZ1mcFZrA'
//curl -X POST 'http://localhost:8083/api/github/qq275860560/resource/saveResource' -H 'Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MDg4NzA3ODYsInVzZXJfbmFtZSI6InVzZXJuYW1lMSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiI5ZDYyNTZhZi01ZDI3LTQ2MGEtYTVlOS1iODkyZTA4NjU4YTciLCJjbGllbnRfaWQiOiJhZG1pbiIsInNjb3BlIjpbIkFETUlOIiwiVVNFUiJdfQ.fG68rdFd33cv1dTsD0eMOV3rZ0QzuW71RYGfKBfUPI9wbKr-4G3fJtmazki21lqn5uFgiYzQ-2NLspZ1mcFZrA'

