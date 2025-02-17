package com.borisgd.citi_assignment.services;

import com.borisgd.citi_assignment.domain.CAUser;

import java.util.*;

public class CAUserUtilities {

    public static int findShortestHops(Map<Integer, List<Integer>> graph, int start, int end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return -1;
        }
        if (start == end) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        int hops = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int current = queue.poll();
                if(current == end) {
                    return hops;
                }
                List<Integer> neighbors = graph.get(current);
                if(neighbors != null) {
                    for(int neighbor : neighbors) {
                        if(!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            hops++;
        }
        return -1;
    }

    public static HashMap<Integer, List<Integer>> buildGraph(List<CAUser> allUsers) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(CAUser user : allUsers) {
            Set<CAUser> friends = user.getFriends();
            List<Integer> friendIds = new ArrayList<>();
            for(CAUser friend : friends) {
                friendIds.add(friend.getId());
                //LOGGER.info("adding friend {} to user {}", friend.getId(), user.getId());
            }
            graph.put(user.getId(), friendIds);
        }
        return graph;
    }
}
