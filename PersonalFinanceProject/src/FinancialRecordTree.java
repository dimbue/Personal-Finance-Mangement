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
