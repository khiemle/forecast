import java.util.*

fun main(args: Array<String>) {
    val rootNode = initializeBinaryTree()

    val result = inOrderWithStack(rootNode)
    result.forEach {
        print("$it, ") // output shoulbe like :4, 2, 5, 1, 3
    }

}

fun inOrderWithStack(root: TreeNode): List<Int> {
    val stk = Stack<TreeNode>()
    val ret = mutableListOf<Int>()

    var current: TreeNode? = root
    while(current != null || stk.isEmpty().not()) {
        while (current != null) {
            stk.add(current)
            current = current.left
        }

        current = stk.pop()
        current?.let {
            ret.add(it.data)
        }
        current = current.right
    }


    return ret
}


//   1
//  2  3
// 4 5
fun initializeBinaryTree(): TreeNode {
    val c = TreeNode(4)
    val d = TreeNode(5)
    val a = TreeNode(2, c, d)
    val b = TreeNode(3)
    return TreeNode(1, a, b)
}


class TreeNode(
    var data: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)