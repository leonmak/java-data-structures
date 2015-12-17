# Linked list
  Node: one value, one pointer
  Head: first data el
  Tail: last data el, not null
  At first head and tail null
  Insert 3, then both head, tail -> 3
  Insert 5, then head -> 5, tail -> 3

```java
public class Node
{
public int Value { get; set; }
public Node Next { get; set; }
}
Node first = new Node { Value = 3 };
Node middle = new Node { Value = 5 };
first.Next = middle; // Pointer

```
* Add to front
* Remove is O(1)
* RemoveLast is O(n), Due to while loop
* Enumerate, While loop

# Doubly Linked list
  null | data | null <-->  null | data | null
  2 pointers, previous and next
* RemoveLast O(1), just set Tail.Previous.Next = null; Tail= Tail.Previous;

# Stack LIFO "Top load"
* can implement w/ list or array
Push, add first
Peek, get first
Pop, remove and return first
* cost is performance w/ LList, array closer together in memory

Postfix algo , 5 6 7 * + 1 - = 46
```
push token int
if token is operator
  pop right-side value
  pop left-side value
  evaluate operator
push result
next
```
### Undo
```
push undo-actions to stack, do actions
When undo click, pop undo-action
```

# Queue FIFO "Bottom load"
Enqueue and Dequeue
Add Last, Remove First

Array implementation more complicated
When dequeue then enqueue, item added to front of array, if add but array full, array double in size then resort using head and tail pointers then add last

### Priority Queue - not FIFO
```
// Add items in Descending value
while loop
if current-item value is less than item-to-add,
then add before current-item

// Dequeue is still Remove First
```
# Tree
Only 1 parent for each child
Only 1 Path from root to any node

## Binary Search Tree
Left child is ALWAYS smaller than Parent, R is more or equal to parent
Add smaller, add to left child
Add equal treat it as larger
```
`   4
  2   6
1 3   4 6
```
If search for 8, only have to look at 3 nodes (logn), not all.

Remove - find node, if leaf just remove, but if not have to find child - 3 cases
1. Node-to-remove has no right child, promote the left child to the parent place
2. Node-to-remove's right child has no left child, promote remove right child to parent
3. Node-to-remove's right child has no right child, promote remove right child's left child to parent

Tree Traversal - 3 ways
Pre   : Process VisitL Visit R
In    : VisitL Process Visit R
Post  : VisitL VisitR Process // dependency graph
__All__ are recursive, can use stack for to make traversal non-recursive

## Use Cases for BST
Sort input, words separated by space, into BST
split input by space, for each word, add to tree

print tree will be in ascending order

# AVL Tree
Self-balancing BST
Search and Enum is same, Insertion and deletion diff
New info: Height, balance factor, R/L heavy

Unbalanced can become a linked list, 1,2,3,4.. always Right
After rebalancing, search is logn

### Balancing Algorithm - Node rotation
Runs for every parent node after Insertion/ Deletion
AVL tree node class implementation
```
  Parent pointer. And L,R, value pointer.
  Balancing methods: L, R, L-R, R-L rotation
  Max child height, L, R height (maxchildheight(L/R))

  Balance factor: Absolute Difference between L&R < 1, 1 is balance factor.
  State: Left heavy, Right heavy, Balanced
```
```
Tree is Right Heavy -> Right child is Left heavy -> L-R Rotation
                    -> Right Child is Right/Not heavy -> L Rotation

Tree is Left Heavy -> Left child is Right heavy -> R-L Rotation
                   -> Left Child is Left/Not heavy -> R Rotation
R| R-L ? L-R : L
L| L-R ? R-L : R
```
R rotation:
1. LChild is New Root
2. Root is New RChild
2. LChild's RChild is RChild's LChild

R-L Rotation:
1. Do L rotation on LChild group
2. R rotation on result

# Hash Table - Associative Array
UniqueKey-value pairs.
### Hashing Criteria - SUSE
Hashing the string key - fixed size result (eg. 32bit int) from input
* Stable (same i/p produce same o/p)
* Uniform (32bit, 2^32~ 4 Bil values), but have some collision, foo oof
* Efficient
* Secure
```
`           S   U   S   E
Additive    1   0   1   0
Folding     1   1   1   0
CRC32       1   1   1   0
MD5         1   1   0   0
SHA-2       1   1   0   1
```
* Add - indexcap -> hashCode -> index -> insert
  handling collisions - chain (linked list) / next index (open address)
  load factor : ratio of filled slots, when beyond increase indexcap (open address)
  if null add to nodepair, if collide, add to nodepair and set index to linked list
* Remove - find index of key, remove from linked list (chaining simpler)
* Search - find index of key, find item in list
* Enumerate - 2 methods for key and value, if !null enumerate list

Demo - Counting words, find index, increment number in value

# Sorting
Arranging data in a collection based on a comparison algo, <, >, =
### 2 General families
1. Linear sort - 1 large operation
2. Divide & Conquer - partition into sets that can be self-sorted

### Quantifying Performance cost (space, time)
1. No. of comparisons
2. No. of swaps

Each algo has best, worst, avg perf depending on how sorted the input is

## Bubble Sort
start from index 0, if i+1 < i, swap i+1 w/ i, until last, then last--, repeat.
eg . 54321 -> 43215 -> 32145 ...

2 for loops
T: W: O(n^2), A: O(n^2), B: O(n)
S: O(n)

## Selection Sort
start from index 0, see if any value from i+1 to last is lesser than i
if have then swap so minimum is at i, i++, repeat

T: W: O(n^2), A: O(n^2), B: O(n^2)
S: O(n)

## Insertion Sort
start from index 1, store i value, j=i, while j-1 value > i value,
copy the j-1 value to j, else i value at j, j--. i++, repeat.
Everything to the left is known/ sorted, right is unknown/ unsorted.

T: W: O(n^2), A: O(n^2), B: O(n)
S: O(n)

```
  Principles
  1.  worst case scenario, general situations, no prior knowledge of input
      if average case & benchmarks, need domain knowledge
  2.  Ignore const factors and lower order terms
  3.  Asymptotic analysis - past a threshold input size, switch to better algo

  Fast algo = slowest growth in worst case
  Holy Grail - (close to) linear

```
# Divide and Conquer
## Merge sort
  Problem: unsorted array of n numbers
  each recursive call, divide input by 2
```
for k = 1 to n
if A(i) < B(j)
C(k) = A(i)
i++

else [B(j) < A(i)]
C(k) = 8(1)
j++
// total # op for 1 level is 6 * input size
// i=1; j=1; if; =; i/j++; k++;
```
    at each 'level' j, input size is n/(2^j), # sub routine (cuts) is 2^j
    total # of operations at level j ≤ (2^j)* 6n/(2^j) = 6n
    total # of operations for all logn+1 levels ≤ 6n (log n + 1)


Data splitting so can be in parallel, also predictable
T: W: O(n logn), A: O(n logn), B: O(n logn)
S: O(n) - Ask: is merging done in-place? duplicates in each phase need extra memory

## Quick Sort
Divide & Conquer, pick pivot value
* Then partition: Left of pivot smaller than pivot, Right bigger.
* Pivot value is sorted.
* Pick pivot in left and right partition. repeat for left, then right.

T: W: O(n^2) (reverse), A: O(n logn), B: O(n logn)
S: O(n). Consider array & stack space.

# String Search Algorithms
Booyer Moore Horspool search
1. Bad Match Char table
```
Eg. For 'TRUTH'
Index | value
?     |   5
T     |   1
R     |   3
U     |   2
WE HOLD THESE TRUTHS TO BE SELF-EVIDENT
TRUTHTRUTHTRUTH (T - 1)
`          TRUTH (R - 3)
`             TRUTH (Done)
```
T: B:O(n/m), n - input string, m - string to find, W:O(nm)

# Concurrency
Multiple instructions executing at once: Multiple Threads, Processes, Systems.

Multi threaded:  create, queue, then process in parallel.
Race conditions arise when multiple callers access the queue at once,
when dequeue never update the head value

### Solutions
Caller sync, double check lock, but readers block other readers
Collection sync:
* Monitor, method level, but readers block other readers
* Read/write locking
Thread safe concurrent classes
