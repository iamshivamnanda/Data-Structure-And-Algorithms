 from ast import List
from typing import Optional


def buildBalancedTree(self,root):
        # create list and store inorder traversel in list
        list = []
        inorder(root,list)
        return makeBalanceTree(list, 0, len(list) -1)

def inorder(root, list):
    if root is None:
        return
    inorder(root.left, list)
    list.add(root.data)
    inorder(root.right, list)

def makeBalanceTree(list, low , high):
    if  low > high:
        return None
    
    mid = (low + high)/2
    node = new Node(list[mid])
    node.left = makeBalanceTree(list, low, mid -1)
    node.right = makeBalanceTree(list, mid + 1, high)
    return node

class Node:
    def __init__(self, val: int):
        self.data = val
        self.left = None
        self.right = None

def findLeastGreater(self, n : int, arr : List[int]) -> List[int]:
    global succ
    root = None
    for i in range(n-1,-1,-1):
        succ = None
        root = insert(root,arr[i])
        if succ not None:
            arr[i] = succ.data
        else:
            arr[i] = -1

    return arr

def insert(root : Node, val: int):
    global succ
    if root is None:
        return Node(val)
    
    if root.data > val:
        succ = root
        root.left = insert(root.left, val)
    else:
        root.right = insert(root.right, val)
    return root

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def bstFromPreorderUtil(preorder: List[int] ,key, min, max, size) -> Optional[TreeNode]:
    global index
    if index > size:
        return None
    
    root = None
    if key> min and key < max:
        root = TreeNode(key)
        index += 1
        if index < size:
            root.left = bstFromPreorderUtil(preorder=preorder,key= preorder[index],min= min, max= key, size = size)
        if index < size:
            root.right = bstFromPreorderUtil(preorder=preorder,key= preorder[index],min= key, max= max, size = size)
    return root

def bstFromPreorder(self, preorder: List[int]) -> Optional[TreeNode]:
    global index 
    index = 0
    return bstFromPreorderUtil(preorder=preorder, key=preorder[index], min= float("-inf"), max= float("inf"), size= len(preorder) -1)

