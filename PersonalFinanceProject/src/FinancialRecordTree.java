import java.util.Date;

public class FinancialRecordTree {
    private static class TreeNode {
        FinancialRecord record;
        TreeNode left, right;

        public TreeNode(FinancialRecord record) {
            this.record = record;
        }
    }

    private TreeNode root;

    // Add a financial record to the tree
    public void add(FinancialRecord record) {
        root = addRecursive(root, record);
    }

    private TreeNode addRecursive(TreeNode node, FinancialRecord record) {
        if (node == null) {
            return new TreeNode(record);
        }

        if (record.getDate().before(node.record.getDate())) {
            node.left = addRecursive(node.left, record);
        } else {
            node.right = addRecursive(node.right, record);
        }
        return node;
    }

    // Remove a financial record from the tree
    public void remove(FinancialRecord record) {
        root = removeRecursive(root, record);
    }

    private TreeNode removeRecursive(TreeNode node, FinancialRecord record) {
        if (node == null) {
            return null;
        }

        if (record.getDate().before(node.record.getDate())) {
            node.left = removeRecursive(node.left, record);
        } else if (record.getDate().after(node.record.getDate())) {
            node.right = removeRecursive(node.right, record);
        } else {
            // Node to be deleted found

            // Case 1: Node has no children (leaf node)
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: Node has one child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Case 3: Node has two children
            // Get the smallest node from the right subtree
            node.record = findMin(node.right).record;
            // Delete the smallest node from the right subtree
            node.right = removeRecursive(node.right, node.record);
        }

        return node;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Search for a record by date
    public FinancialRecord search(Date date) {
        return searchRecursive(root, date);
    }

    private FinancialRecord searchRecursive(TreeNode node, Date date) {
        if (node == null) {
            return null;
        }

        if (node.record.getDate().equals(date)) {
            return node.record;
        }

        if (date.before(node.record.getDate())) {
            return searchRecursive(node.left, date);
        } else {
            return searchRecursive(node.right, date);
        }
    }

    // Print all records in-order
    public void printInOrder() {
        printInOrderRecursive(root);
    }

    private void printInOrderRecursive(TreeNode node) {
        if (node != null) {
            printInOrderRecursive(node.left);
            System.out.println(node.record.getDetails());
            printInOrderRecursive(node.right);
        }
    }
}
