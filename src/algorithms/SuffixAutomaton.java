package algorithms;

import java.util.HashMap;
import java.util.Map;

public class SuffixAutomaton {

    private static class State {

        int length;

        int link;

        Map<Character, Integer> next = new HashMap<>();
    }

    private State[] states;

    private int size;

    private int last;

    public SuffixAutomaton(String text) {

        int maxStates = Math.max(2, 2 * text.length() + 1);

        states = new State[maxStates];

        for (int i = 0; i < states.length; i++) {
            states[i] = new State();
        }

        size = 1;
        last = 0;

        states[0].link = -1;

        for (char c : text.toCharArray()) {
            extend(c);
        }
    }

    private void extend(char c) {

        int current = size++;

        states[current].length =
                states[last].length + 1;

        int p = last;

        while (p != -1
                && !states[p].next.containsKey(c)) {

            states[p].next.put(c, current);

            p = states[p].link;
        }

        if (p == -1) {

            states[current].link = 0;

        } else {

            int q = states[p].next.get(c);

            if (states[p].length + 1 == states[q].length) {

                states[current].link = q;

            } else {

                int clone = size++;

                states[clone].length =
                        states[p].length + 1;

                states[clone].next =
                        new HashMap<>(states[q].next);

                states[clone].link =
                        states[q].link;

                while (p != -1
                        && states[p].next.getOrDefault(c, -1) == q) {

                    states[p].next.put(c, clone);

                    p = states[p].link;
                }

                states[q].link = clone;

                states[current].link = clone;
            }
        }

        last = current;
    }

    public boolean contains(String pattern) {

        int current = 0;

        for (char c : pattern.toCharArray()) {

            if (!states[current].next.containsKey(c)) {
                return false;
            }

            current = states[current].next.get(c);
        }

        return true;
    }
}