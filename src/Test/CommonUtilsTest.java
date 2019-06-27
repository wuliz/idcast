package Test;

import cn.itcast.commons.CommonUtils;
import entity.Person;
import org.junit.Test;

import java.util.HashMap;

public class CommonUtilsTest {
    @Test
    public void uuidTest(){
        String uuid = CommonUtils.uuid();
        System.out.println(uuid);
    }

    @Test
    public void tobeanTest(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name","zs");
        map.put("age","20");
        map.put("sex","男");
        map.put("other","trye");
        Person person = CommonUtils.toBean(map, Person.class);
        System.out.println(person);
    }
}
