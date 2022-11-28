// #include <bits/stdc++.h>

struct Node
{
    int data;
    Node* left,  *right;
};


Node* buildBalancedTree(Node* root)
{
	vector<Node *> list;
    inorder(root, list)
    return buildBalancedTreeUtil(list, 0, list.size() -1);
}

void inorder(Node* root, vector<Node*> &list){
    if(root == null)
        return ;
    
    inorder(root->left, list);
    list.push_back(root);
    inorder(root->right, list);

}

Node* buildBalancedTreeUtil( vector<Node*> &list, int min, int max){
    if(min > max)
        return null;

    int mid  = (min + max)/2;
    Node *root = list[mid]
    root->left = buildBalancedTreeUtil(list, min, mid -1);
    root->right = buildBalancedTreeUtil(list, mid + 1,max);
    return root;
}