package com.projectsoa.avabuddies.data.models.responses.friend;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FriendsResponse {

    @SerializedName("friends")
    public List<FriendResponse> friends;
}
