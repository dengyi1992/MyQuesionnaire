package com.example.deng.myquesionnaire.bean;

import java.util.List;

/**
 * Created by deng on 16-10-24.
 */

public class Qs {

    /**
     * success : true
     * questionnaires : [{"_id":"57fb2fc27f151d7f7c94fcbc","title":"问卷一","deadline":"2016/10/11 14:08","isPublished":true,"questions":["57fb2ee17f151d7f7c94fcb0","57fb2f9c7f151d7f7c94fcbb","57fb2fe07f151d7f7c94fcbd","57fb30097f151d7f7c94fcbe"]},{"_id":"57fb32eb7f151d7f7c94fcc6","title":"问卷二","deadline":"2016/10/12 14:19","isPublished":true,"questions":["57fb30097f151d7f7c94fcbe"]},{"_id":"57fb33477f151d7f7c94fccb","title":"问卷三","deadline":"2016/10/12 14:21","isPublished":true,"questions":["57fb2ee17f151d7f7c94fcb0","57fb30097f151d7f7c94fcbe"]},{"_id":"57fb37c21526b00a4a85bca2","title":"問卷四","deadline":"2016/10/10 14:50","isPublished":true,"questions":["57fb2ee17f151d7f7c94fcb0","57fb2f9c7f151d7f7c94fcbb","57fb2fe07f151d7f7c94fcbd","57fb30097f151d7f7c94fcbe"]},{"_id":"57fb38921526b00a4a85bca3","title":"問卷五","deadline":"2016/10/11 14:51","isPublished":true,"questions":["57fb2ee17f151d7f7c94fcb0","57fb2f9c7f151d7f7c94fcbb","57fb3a08d493860da0c2c258","57fb3a33d493860da0c2c25d"]},{"_id":"57fb3a89d493860da0c2c262","title":"問卷六","deadline":"2016/10/10 15:00","isPublished":true,"questions":["57fb3a08d493860da0c2c258","57fb3a33d493860da0c2c25d"]},{"_id":"57fb3ae7d493860da0c2c265","title":"問卷七","deadline":"2016/10/10 15:00","isPublished":true,"questions":["57fb2ee17f151d7f7c94fcb0","57fb3a08d493860da0c2c258","57fb3a33d493860da0c2c25d"]},{"_id":"58019791af412b265d4992c0","title":"问卷测试","deadline":"2016/10/31 10:42","isPublished":true,"questions":["57fb2ee17f151d7f7c94fcb0","57fb3a08d493860da0c2c258","57fb3a33d493860da0c2c25d"]},{"_id":"5801a123ab03592bff671d05","title":"问卷八","deadline":"2016/10/15 11:30","isPublished":true,"questions":["57fb2f9c7f151d7f7c94fcbb","58019e2bab03592bff671cb3","58019e9dab03592bff671cbc","58019ee2ab03592bff671cc4","58019f0fab03592bff671ccf","58019f4bab03592bff671cd7","58019f73ab03592bff671cdd","58019fa5ab03592bff671ce1","58019ff2ab03592bff671ce6","5801a018ab03592bff671ceb","5801a0a1ab03592bff671cfa","5801a0eaab03592bff671d02"]},{"_id":"5806d536b7a8763b46f5e880","title":"问卷九","deadline":"2016/10/19 10:40","isPublished":true,"questions":["58019e2bab03592bff671cb3","58019e84ab03592bff671cbb","58019ee2ab03592bff671cc4","58019f0fab03592bff671ccf","58019f73ab03592bff671cdd","58019fa5ab03592bff671ce1","5801a073ab03592bff671cf7","5801a0a1ab03592bff671cfa"]},{"_id":"5806d891b7a8763b46f5e882","title":"111","deadline":"2016/10/20 10:21","isPublished":true,"questions":["57fb2ee17f151d7f7c94fcb0","57fb2f9c7f151d7f7c94fcbb","57fb2fe07f151d7f7c94fcbd"]},{"_id":"5806d914b7a8763b46f5e883","title":"222","deadline":"2016/10/20 11:30","isPublished":true,"questions":["58019ff2ab03592bff671ce6","5801a018ab03592bff671ceb","5801a042ab03592bff671cf1"]}]
     */

    private boolean success;
    /**
     * _id : 57fb2fc27f151d7f7c94fcbc
     * title : 问卷一
     * deadline : 2016/10/11 14:08
     * isPublished : true
     * questions : ["57fb2ee17f151d7f7c94fcb0","57fb2f9c7f151d7f7c94fcbb","57fb2fe07f151d7f7c94fcbd","57fb30097f151d7f7c94fcbe"]
     */

    private List<QuestionnairesBean> questionnaires;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<QuestionnairesBean> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<QuestionnairesBean> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public static class QuestionnairesBean {
        private String _id;
        private String title;
        private String deadline;
        private boolean isPublished;
        private List<String> questions;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public boolean isIsPublished() {
            return isPublished;
        }

        public void setIsPublished(boolean isPublished) {
            this.isPublished = isPublished;
        }

        public List<String> getQuestions() {
            return questions;
        }

        public void setQuestions(List<String> questions) {
            this.questions = questions;
        }
    }
}
