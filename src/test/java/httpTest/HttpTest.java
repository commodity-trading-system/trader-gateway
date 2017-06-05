package httpTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cts.service.FutureService;
import com.cts.service.OrderService;
import com.cts.util.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-mvc.xml"})
public class HttpTest {

    @Resource
    private FutureService futureService;

//    public void testFloatingPrice(){
//        String tmp = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/futures/1/latest",null);
//        JSONObject obj  = JSONObject.parseObject(tmp);
//        JSONObject node = obj.getJSONObject("node");
//        Integer waitIndex = node.getInteger("modifiedIndex");
//        while(true){
//            waitIndex++;
//            tmp = HttpUtil.sendGet("http://139.224.236.65:2379/v2/keys/futures/1/latest", "wait=true&waitIndex="+waitIndex.toString());
//            obj = JSONObject.parseObject(tmp);
//            System.out.println(obj.getInteger("waitIndex"));
//        }
//    }

    @Test
    public void testAllOrders(){
        String tmp = HttpUtil.sendGet("http://139.224.236.65:5002/firms/1/consignations","?limit=20&offset=0");
        JSONArray array = JSONArray.parseArray(tmp);
        for(Object obj:array){
            JSONObject data = (JSONObject)obj;
            data.put("FutureId", futureService.getFutureById(data.getLong("FutureId")));
        }

    }
}
