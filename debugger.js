
var twoSum = function(nums, target) {
    let map = new Map();
    let ans = [];
    for (let i=0; i<nums.length; i++){
        if (map.has(target - nums[i])) {
            ans.push(map.get(target-nums[i]), i);
        }else{
            map.set(nums[i], i);
        }
    }
    return ans;
};
var topKFrequent = function(nums, k) {
    let map = new Map();
    for (let i=0; i<nums.length; i++){
        if (map.has(nums[i])){
            map.set(nums[i], map.get(nums[i])+1);
        }else{
            map.set(nums[i], 1);
        }
    }
    let bucket = [];
    map.forEach(function (value, key){
        bucket[value] = key;
    });
    console.log(map);
    console.log(bucket);
    let ans = [];
    for (let j=bucket.length-1; j>=bucket.length-k; j--){
        if (bucket[j] > 0) ans.push(bucket[j]);
    }
    return ans;
};

var isValidSudoku = function(board) {    
    let cols = new Map();
    let blocks = new Map();
    for (let i=0; i<board.length; i++){
        let row = new Set();
        for (let j=0; j<board[i].length; j++){            
            if (board[i][j] == ".") continue;
            if (row.has(board[i][j])){
                return false;
            } 
            else{
                row.add(board[i][j]);
            }
            let col = cols.has(j) ? cols.get(j) : new Set();
            if (col.has(board[i][j])){
                return false;
            } 
            else{
                col.add(board[i][j]);
                cols.set(j, col);
            }
            let x = Math.floor(i/3);
            let y = Math.floor(j/3);
            let block = blocks.has(x + "_" + y) ? blocks.get(x + "_" + y) : new Set();
            if (block.has(board[i][j])){
                return false;
            } 
            else{
                block.add(board[i][j]);
                blocks.set(x + "_" + y, block);              
            }
        }
    }
    console.log(blocks);
    return true;
}
let sudoku = [
    [".",".",".", ".","5",".", ".","1","."],
    [".","4",".", "3",".",".", ".",".","."],
    [".",".",".", ".",".","3", ".",".","1"],

    ["8",".",".", ".",".",".", ".","2","."],
    [".",".","2", ".","7",".", ".",".","."],
    [".","1","5", ".",".",".", ".",".","."],

    [".",".",".", ".",".","2", ".",".","."],
    [".","2",".", "9",".",".", ".",".","."],
    [".",".","4", ".",".",".", ".",".","."]
];
const map = new Map();
map.set("0_0", "00");
map.set("0_1", "01");
map.set("1_0", "10");
//console.log(map.has("0_0"));
console.log(isValidSudoku(sudoku));