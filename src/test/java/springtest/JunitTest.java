package springtest;

import com.cts.dao.TraderDao;
import com.cts.entity.Trader;
import com.cts.service.TraderService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/6/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"/spring-mvc.xml"})
public class JunitTest {
    private static final Logger logger = Logger.getLogger("JUNIT");

    @Resource
    private TraderService traderService;

    @Test
    public void getTraderByName(){
        Trader trader = traderService.getTraderByName("bob");
        logger.log(Level.DEBUG, "bob belongs to firm "+trader.getFirmId());
    }
}
