package com.example.hlw;

import com.example.hlw.dto.UserDto;
import com.example.hlw.utils.EntityUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class DemoTrimApplicationTests {

	@Test
	public void contextLoads()throws Exception {
		String a = EntityUtils.getObjectJsonString(UserDto.class);
		System.out.println(a);
		UserDto userDto = new Gson().fromJson(a,UserDto.class);


		UserDto userDto1 = EntityUtils.newInstane(UserDto.class);
	}


	@Test
	public void getObjectJsonString (){
		String result = EntityUtils.getObjectJsonString(UserDto.class);
		System.out.println(result);
	}

	@Test
	public void getObjectApiDoc(){
		String a ="aa";
		a = StringUtils.rightPad(a,8," ");
		System.out.println(a);
		String result = EntityUtils.getObjectJsonDsecString(UserDto.class);
		System.out.println(result);
	}

	@Test
	public void ObjectString(){
		UserDto userDto = EntityUtils.returnInstance(UserDto.class);
	}
}
