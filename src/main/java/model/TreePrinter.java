package model;

import model.algorithm.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TreePrinter {

    public static void printTree(Node root) {
        int maxDepth = findMaxDepth(root);
        List<List<Node>> nodesAtLevels = new ArrayList<>();
        for (int i = 0; i < maxDepth; i++) {
            nodesAtLevels.add(new ArrayList<>());
        }
        fillNodesAtLevels(root, nodesAtLevels, 0);
        Collections.reverse(nodesAtLevels);
        printNodes(nodesAtLevels);
    }

    private static int findMaxDepth(Node node) {
        if (node == null) {
            return 0;
        }
        int maxChildDepth = 0;
        for (Node child : node.getChildren()) {
            int childDepth = findMaxDepth(child);
            maxChildDepth = Math.max(maxChildDepth, childDepth);
        }
        return 1 + maxChildDepth;
    }

    private static void fillNodesAtLevels(Node node, List<List<Node>> nodesAtLevels, int currentDepth) {
        if (node != null) {
            nodesAtLevels.get(currentDepth).add(node);
            for (Node child : node.getChildren()) {
                fillNodesAtLevels(child, nodesAtLevels, currentDepth + 1);
            }
        }
    }

    private static void printNodes(List<List<Node>> nodesAtLevels) {
        for (int i = nodesAtLevels.size() - 1; i >= 0; i--) {
            List<Node> nodes = nodesAtLevels.get(i);

            for (Node node : nodes) {
                if (node != null) {
                    System.out.print("(" + node.getEvaluation() + Arrays.toString(node.getMove()) + ") ");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();

            // Print branch lines
            if (i > 0) {
                for (Node ignored : nodes) {
                    System.out.print("| ");
                }
                System.out.println();
            }
        }
    }

}
