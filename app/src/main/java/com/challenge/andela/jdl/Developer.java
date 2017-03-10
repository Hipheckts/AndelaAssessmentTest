package com.challenge.andela.jdl;
import java.util.List;

/**
 * Created by mokeam on 3/4/17.
 */
//This class implements the setters and getters methods of the github api.

public class Developer {

    private List<ItemsEntity> items;

    public List<ItemsEntity> getItems() {
        return items;
    }


    public static class ItemsEntity {
        private String login;
        private int id;
        private String avatar_url;
        private String html_url;


        public String getLogin() {
            return login;
        }

        public int getId() {
            return id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public String getHtml_url() {
            return html_url;
        }

    }
}