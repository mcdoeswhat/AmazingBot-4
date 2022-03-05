package me.albert.amazingbot.objects.info.group;

import java.util.List;

public class GroupSystemMessage {
    protected List<InviteRequest> invited_requests;
    protected List<JoinRequest> join_requests;

    public List<InviteRequest> getInvitedRequests() {
        return invited_requests;
    }

    public List<JoinRequest> getJoinRequests() {
        return join_requests;
    }
}
