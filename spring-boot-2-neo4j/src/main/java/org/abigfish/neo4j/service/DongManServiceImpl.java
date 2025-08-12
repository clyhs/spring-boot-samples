package org.abigfish.neo4j.service;
import org.abigfish.neo4j.dao.DongManRepository;
import org.abigfish.neo4j.entity.DongMan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className:
 * @author: zhaopeng
 * @description: TODO(......)
 * @date: 2021年12月27日 9:49
 */
@Service
@Transactional(value="transactionManager")
public class DongManServiceImpl implements DongManService {

    @Resource
    DongManRepository dongManRepository;

    @Override
    public DongMan addDongMan(DongMan dongMan) {
        return dongManRepository.save(dongMan);
    }

    @Override
    public DongMan getInfoById(long id) {
        return dongManRepository.findById(id).get();
    }

    @Override
    public void delById(long id) {
        dongManRepository.deleteById(id);
    }

    @Override
    public void createRelation(String from, String relation, String to) {
        dongManRepository.createRelation(from,relation,to);
    }

    @Override
    public void createRelationByName(String fromName) {
        dongManRepository.createRelationByName(fromName);
    }

    @Override
    public Page<DongMan> getListByPage(int current, int pageSize, String Name) {
        Pageable pageable= PageRequest.of(current,pageSize);
        return dongManRepository.findAll(pageable);
    }

    @Override
    public List<String> getAllRealationTypes() {
        return dongManRepository.getAllRealationTypes();
    }

    @Override
    public Boolean existById(long id) {
        return dongManRepository.existsById(id);
    }

    @Override
    public DongMan updateById(DongMan dongMan) {
        return dongManRepository.updateById(dongMan);
    }

    @Override
    public List<DongMan> getRelationsByName(String name,String relation) {
        return dongManRepository.getRelationsByName(name,relation);
    }
}
