package com.iwa.iwatesting.serializer;

import com.iwa.iwatesting.model.ApiResponse;
import com.iwa.iwatesting.model.DetailUser;
import com.iwa.iwatesting.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ObjectSerializerHandler implements ObjectSerialier {
    @Override
    public ApiResponse deserializer(String response) {
        try {
            JSONArray objects = new JSONArray(response);
            List<User> users = new ArrayList<>();
            for (int i = 0; i < objects.length(); i++) {
                User user = new User();
                JSONObject object = objects.getJSONObject(i);
                user.setLogin(object.getString("login"));
                user.setId(object.getInt("id"));
                user.setNode_id(object.getString("node_id"));
                user.setAvatar_url(object.getString("avatar_url"));
                user.setGravatar_id(object.getString("gravatar_id"));
                user.setUrl(object.getString("url"));
                user.setHtml_url(object.getString("html_url"));
                user.setFollowers_url(object.getString("followers_url"));
                user.setFollowers_url(object.getString("following_url"));
                user.setGists_url(object.getString("gists_url"));
                user.setStarred_url(object.getString("starred_url"));
                user.setSubscriptions_url(object.getString("subscriptions_url"));
                user.setOrganizations_url(object.getString("organizations_url"));
                user.setRepos_url(object.getString("repos_url"));
                user.setEvents_url(object.getString("events_url"));
                user.setReceived_events_url(object.getString("received_events_url"));
                user.setType(object.getString("type"));
                user.setSite_admin(object.getBoolean("site_admin"));
                users.add(user);
            }
            return new ApiResponse(users);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DetailUser deserializerDetailUse(String res) {
        try {
            JSONObject object = new JSONObject(res);
            DetailUser user = new DetailUser();
            user.setLogin(object.getString("login"));
            user.setId(object.getInt("id"));
            user.setNode_id(object.getString("node_id"));
            user.setAvatar_url(object.getString("avatar_url"));
            user.setGravatar_id(object.getString("gravatar_id"));
            user.setUrl(object.getString("url"));
            user.setHtml_url(object.getString("html_url"));
            user.setFollowers_url(object.getString("followers_url"));
            user.setFollowers_url(object.getString("following_url"));
            user.setGists_url(object.getString("gists_url"));
            user.setStarred_url(object.getString("starred_url"));
            user.setSubscriptions_url(object.getString("subscriptions_url"));
            user.setOrganizations_url(object.getString("organizations_url"));
            user.setRepos_url(object.getString("repos_url"));
            user.setEvents_url(object.getString("events_url"));
            user.setReceived_events_url(object.getString("received_events_url"));
            user.setType(object.getString("type"));
            user.setSite_admin(object.getBoolean("site_admin"));


            user.setName(object.getString("name"));
            user.setCompany(object.getString("company"));
            user.setBlog(object.getString("blog"));
            user.setLocation(object.getString("location"));
            user.setEmail(object.getString("email"));
            user.setHireable(object.getString("hireable"));
            user.setBio(object.getString("bio"));
            user.setPublic_repos(object.getInt("public_repos"));
            user.setPublic_gists(object.getInt("public_gists"));

            user.setFollowers(object.getInt("followers"));
            user.setFollowing(object.getInt("following"));
            user.setCreated_at(object.getString("created_at"));
            user.setUpdated_at(object.getString("updated_at"));
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
