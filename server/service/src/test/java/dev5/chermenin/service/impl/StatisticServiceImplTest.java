package dev5.chermenin.service.impl;

import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.api.StatisticService;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ancarian on 22.10.2017.
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
public class StatisticServiceImplTest {
    private final static Pageable pageable = new PageRequest(0, 100);

    @Autowired
    private StatisticService statisticService;
    @Autowired
    private GroupService groupService;

    @Test
    public void getEnrollUsers() throws Exception {
        List<UserDto> users = statisticService.getEnrollUsers(1);
        for (int i = 0; i < 3; i++) {
            assertEquals(users.get(i).getId(), new Long(6 - i));
        }
        assertEquals(3, users.size());
    }


    @Test
    public void getTopGroupsByMark() throws Exception {
        groupService.calculatePassMark(1);
        groupService.calculatePassMark(2);
        groupService.calculatePassMark(3);
        List<GroupDto> groups = statisticService.getTopGroupsByMark(pageable);

        assertEquals((Integer) groups.get(0).getEnrollMark(), (Integer) 360);
        assertEquals((Integer) groups.get(1).getEnrollMark(), (Integer) 180);
        assertEquals((Integer) groups.get(2).getEnrollMark(), (Integer) 0);

    }

    @Test
    public void getTopUsersBySubject() throws Exception {
    }

    @Test
    public void getTopGroupsByAvgMark() throws Exception {
    }

}