package jayim.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jayim.dao.GroupMapper;
import jayim.dao.UserMapper;
import jayim.model.Group;
import jayim.model.User;
import jayim.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * 群组Service
 * @author jay
 */
@Service
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService{

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Mapper<Group> getMapper() {
        return groupMapper;
    }

    @Override
    public PageInfo<Group> search(String searchStr, int pageNum, int pageSize)
    {

        //分页处理
        PageHelper.startPage(pageNum, pageSize);
        //执行查询
        //取分页信息
        PageInfo<Group> pageInfo=new PageInfo<>();
        Example example=new Example(Group.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andLike("groupName","%"+searchStr+"%");
        pageInfo.setList(groupMapper.selectByExample(example));
        return pageInfo;
    }

    @Override
    public User getGroupMaster(int groupId) {
        Integer groupMasterId = groupMapper.getGroupMasterId(groupId);
        return userMapper.selectByPrimaryKey(groupMasterId);
    }

    @Override
    public int save(Group group) {
        return groupMapper.insert(group);
    }

    @Override
    public int deleteById(Object id) {
        return groupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Group group) {
        return groupMapper.updateByPrimaryKeySelective(group);
    }

    @Override
    public List<Group> selectAll() {
        return groupMapper.selectAll();
    }

    @Override
    public Group getById(Object id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    @Override
    public Group selectOne(Group group) {
        return null;
    }

    @Override
    public Group findByProperty(String propertyName, Object value) {
        return null;
    }

    @Override
    public List<Group> findListByProperty(String propertyName, Object value) {
        return null;
    }

    @Override
    public List<Group> select(Group group) {
        return null;
    }

    @Override
    public List<Group> selectAndPaging(Group group, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public PageInfo<Group> selectAndPagingAndPackage(Group group, Integer pageNum, Integer pageSize) {
        return null;
    }
}
