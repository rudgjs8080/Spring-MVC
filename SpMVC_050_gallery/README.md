# Spring file upload Project
* Web client에서 파일을 선택하면 서버에 Upload하기
* 이미지 게시판, 이미지 갤러리 등을 만들 때 사용한다

## 필요한 Dependency
	<!-- https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple -->
		<dependency>
			<groupId>com.googlecode.json-simple</groupId>
			<artifactId>json-simple</artifactId>
			<version>1.1.1</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.4</version>
		</dependency>
		
* 2개가 필요하다

* root-context.xml

	<!-- file upload와 관련한 bean을 설정하기 -->
	<bean id="multipartResolver"
		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSizePerFile" value="3000000"/>
		<property name="maxUploadSize" value="30000000"/>
	</bean>
	
* maxUploadPerFile : 파일 1개의 용량 제한
* maxUploadFile : 전체 파일 용량 제한, 다수의 파일을 올릴때
	

