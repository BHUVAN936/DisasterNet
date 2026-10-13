package algorithms;

import java.util.*;

public class AhoCorasick {

    private static class Node {

        Map<Character, Integer> next = new HashMap<>();

        int fail = 0;

        List<String> outputs = new ArrayList<>();
    }

    private final List<Node> nodes = new ArrayList<>();

    public AhoCorasick(List<String> patterns) {

        nodes.add(new Node());

        for (String pattern : patterns) {
            insert(pattern.toLowerCase());
        }

        buildFailureLinks();
    }

    private void insert(String pattern) {

        int current = 0;

        for (char c : pattern.toCharArray()) {

            if (!nodes.get(current).next.containsKey(c)) {

                nodes.get(current).next.put(c, nodes.size());

                nodes.add(new Node());
            }

            current = nodes.get(current).next.get(c);
        }

        nodes.get(current).outputs.add(pattern);
    }

    private void buildFailureLinks() {

        Queue<Integer> queue = new LinkedList<>();

        for (Map.Entry<Character, Integer> entry
                : nodes.get(0).next.entrySet()) {

            int child = entry.getValue();

            nodes.get(child).fail = 0;

            queue.add(child);
        }

        while (!queue.isEmpty()) {

            int current = queue.poll();

            for (Map.Entry<Character, Integer> entry
                    : nodes.get(current).next.entrySet()) {

                char c = entry.getKey();
                int child = entry.getValue();

                queue.add(child);

                int fallback = nodes.get(current).fail;

                while (fallback != 0
                        && !nodes.get(fallback).next.containsKey(c)) {

                    fallback = nodes.get(fallback).fail;
                }

                if (nodes.get(fallback).next.containsKey(c)
                        && nodes.get(fallback).next.get(c) != child) {

                    nodes.get(child).fail =
                            nodes.get(fallback).next.get(c);

                } else {

                    nodes.get(child).fail = 0;
                }

                nodes.get(child).outputs.addAll(
                        nodes.get(nodes.get(child).fail).outputs
                );
            }
        }
    }

    public Map<String, Integer> search(String text) {

        Map<String, Integer> result = new LinkedHashMap<>();

        for (String pattern : getPatterns()) {
            result.put(pattern, 0);
        }

        int current = 0;

        text = text.toLowerCase();

        for (char c : text.toCharArray()) {

            while (current != 0
                    && !nodes.get(current).next.containsKey(c)) {

                current = nodes.get(current).fail;
            }

            if (nodes.get(current).next.containsKey(c)) {

                current = nodes.get(current).next.get(c);

            } else {

                current = 0;
            }

            for (String matched : nodes.get(current).outputs) {

                result.put(
                        matched,
                        result.getOrDefault(matched, 0) + 1
                );
            }
        }

        return result;
    }

    private List<String> getPatterns() {

        Set<String> patterns = new LinkedHashSet<>();

        for (Node node : nodes) {
            patterns.addAll(node.outputs);
        }

        return new ArrayList<>(patterns);
    }
}