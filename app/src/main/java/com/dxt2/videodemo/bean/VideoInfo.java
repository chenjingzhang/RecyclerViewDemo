package com.dxt2.videodemo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7 0007.
 */

public class VideoInfo {

    private String msg;
    private ResultBean result;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        private int item_count;


        private List<VideoListByTimeBean> video_list_by_time;

        public int getItem_count() {
            return item_count;
        }

        public void setItem_count(int item_count) {
            this.item_count = item_count;
        }

        public List<VideoListByTimeBean> getVideo_list_by_time() {
            return video_list_by_time;
        }

        public void setVideo_list_by_time(List<VideoListByTimeBean> video_list_by_time) {
            this.video_list_by_time = video_list_by_time;
        }

        public static class VideoListByTimeBean {
            @SerializedName("class")
            private int classX;
            private String create_time;
            private String create_time_desc;
            private String link;
            private int play_times;
            private int play_times_num;
            private String show_type;
            private String thumb_img;
            private String time_len;
            private String title;
            private String uid;
            private String url;


            private UrlInfoBean url_info;


            private UserInfoBean user_info;
            private String username;
            private String zone_time;


            private List<SegsBean> segs;

            public int getClassX() {
                return classX;
            }

            public void setClassX(int classX) {
                this.classX = classX;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getCreate_time_desc() {
                return create_time_desc;
            }

            public void setCreate_time_desc(String create_time_desc) {
                this.create_time_desc = create_time_desc;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public int getPlay_times() {
                return play_times;
            }

            public void setPlay_times(int play_times) {
                this.play_times = play_times;
            }

            public int getPlay_times_num() {
                return play_times_num;
            }

            public void setPlay_times_num(int play_times_num) {
                this.play_times_num = play_times_num;
            }

            public String getShow_type() {
                return show_type;
            }

            public void setShow_type(String show_type) {
                this.show_type = show_type;
            }

            public String getThumb_img() {
                return thumb_img;
            }

            public void setThumb_img(String thumb_img) {
                this.thumb_img = thumb_img;
            }

            public String getTime_len() {
                return time_len;
            }

            public void setTime_len(String time_len) {
                this.time_len = time_len;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public UrlInfoBean getUrl_info() {
                return url_info;
            }

            public void setUrl_info(UrlInfoBean url_info) {
                this.url_info = url_info;
            }

            public UserInfoBean getUser_info() {
                return user_info;
            }

            public void setUser_info(UserInfoBean user_info) {
                this.user_info = user_info;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getZone_time() {
                return zone_time;
            }

            public void setZone_time(String zone_time) {
                this.zone_time = zone_time;
            }

            public List<SegsBean> getSegs() {
                return segs;
            }

            public void setSegs(List<SegsBean> segs) {
                this.segs = segs;
            }

            public static class UrlInfoBean {
                private String Referer;
                private String User_Agent;
                private String url;

                public String getReferer() {
                    return Referer;
                }

                public void setReferer(String Referer) {
                    this.Referer = Referer;
                }

                public String getUser_Agent() {
                    return User_Agent;
                }

                public void setUser_Agent(String User_Agent) {
                    this.User_Agent = User_Agent;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class UserInfoBean {
                private String avatar;
                @SerializedName("class")
                private int classX;
                private String dm_link;
                private int order;
                private String type;
                private String uid;
                private String username;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public int getClassX() {
                    return classX;
                }

                public void setClassX(int classX) {
                    this.classX = classX;
                }

                public String getDm_link() {
                    return dm_link;
                }

                public void setDm_link(String dm_link) {
                    this.dm_link = dm_link;
                }

                public int getOrder() {
                    return order;
                }

                public void setOrder(int order) {
                    this.order = order;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }
            }

            public static class SegsBean {
                private String desc;
                private String seg_type;
                private String type;
                private String url;

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getSeg_type() {
                    return seg_type;
                }

                public void setSeg_type(String seg_type) {
                    this.seg_type = seg_type;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
