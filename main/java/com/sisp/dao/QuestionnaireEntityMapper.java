package com.sisp.dao;


import com.sisp.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionnaireEntityMapper {


    /**
     * 插入问卷
     * @param questionnaireEntity
     * @return
     */
    int insert(QuestionnaireEntity questionnaireEntity);


    /**
     * 查询问卷
     * @param questionnaireEntity
     * @return
     */
    List<QuestionnaireEntity> queryQuestionnaireList(QuestionnaireEntity questionnaireEntity);


    /**
     * 修改问卷
     * @param questionnaireEntity
     * @return
     */
    int updateQuestionnaire(QuestionnaireEntity questionnaireEntity);


    /**
     * 删除问卷
     * @param questionnaireEntity
     * @return
     */
    int deleteQuestionnaire(QuestionnaireEntity questionnaireEntity);

}
