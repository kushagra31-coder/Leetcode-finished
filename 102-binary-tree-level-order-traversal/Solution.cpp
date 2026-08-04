/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<vector<int>> levelOrder(TreeNode* r){
    vector<vector<int>> res;
    if(!r) return res;
    queue<TreeNode*> q; q.push(r);
    while(!q.empty()){
        int n=q.size();
        vector<int> lvl;
        while(n--){
            auto t=q.front(); q.pop();
            lvl.push_back(t->val);
            if(t->left) q.push(t->left);
            if(t->right) q.push(t->right);
        }
        res.push_back(lvl);
    }
    return res;
    }
};