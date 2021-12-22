<h1>Sudonku</h1>
Sudonku, a straightforward library to generate and solve Sudoku puzzles

<h2>Constructor Details</h2>
<ul>
<li>
<h3>Sudonku</h3>
<div><span>public</span>&nbsp;<span>Sudonku</span>&#8203;<span>(int&nbsp;fillPercentage)</span></div>
<div>Create a board filled to a given percentage</div>
<dl>
<dt>Parameters:</dt>
<dd><code>fillPercentage</code> - Integer from 0-100</dd>
</dl>
</li>
<li>
<h3>Sudonku</h3>
<div><span>public</span>&nbsp;<span>Sudonku</span>&#8203;<span>(int[][]&nbsp;board)</span></div>
<div>Import a board as a 9x9 array of integers from 0-9</div>
<dl>
<dt>Parameters:</dt>
<dd><code>board</code> - 9x9 array of integers from 0-9</dd>
</dl>
</li>
</ul>
</li>

<h2>Method Details</h2>
<ul>
<li>
<h3>generateBoard</h3>
<div><span>public</span>&nbsp;<span>void</span>&nbsp;<span>generateBoard</span>()</div>
<div>Create a new, randomised board</div>
</li>
<li>
<h3>toString</h3>
<div><span>public</span>&nbsp;<span><a href="https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/String.html" title="class or interface in java.lang">String</a></span>&nbsp;<span>toString</span>()</div>
<dl>
<dt>Overrides:</dt>
<dd><code><a href="https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/Object.html#toString()" title="class or interface in java.lang">toString</a></code>&nbsp;in class&nbsp;<code><a href="https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/Object.html" title="class or interface in java.lang">Object</a></code></dd>
</dl>
</li>
<li>
<h3>solve</h3>
<div><span>public</span>&nbsp;<span>void</span>&nbsp;<span>solve</span>()</div>
<div>Solves the board by filling all 0's with a valid value</div>
</li>
<li>
<h3>getFillPercentage</h3>
<div><span>public</span>&nbsp;<span>double</span>&nbsp;<span>getFillPercentage</span>()</div>
<div>Getter for Fill Percentage</div>
<dl>
<dt>Returns:</dt>
<dd>Fill Percentage</dd>
</dl>
</li>
<li>
<h3>setFillPercentage</h3>
<div><span>public</span>&nbsp;<span>void</span>&nbsp;<span>setFillPercentage</span>&#8203;<span>(int&nbsp;fillPercentage)</span></div>
<div>Setter for Fill Percentage</div>
<dl>
<dt>Parameters:</dt>
<dd><code>fillPercentage</code> - Integer from 0-100</dd>
</dl>
</li>
<li>
<h3>getBoard</h3>
<div><span>public</span>&nbsp;<span>int[][]</span>&nbsp;<span>getBoard</span>()</div>
<div>Returns the board in a 9x9 array of integers with values ranging from 0-9</div>
<dl>
<dt>Returns:</dt>
<dd>Getter for the board</dd>
</dl>
</li>
<li>
<h3>setBoard</h3>
<div><span>public</span>&nbsp;<span>void</span>&nbsp;<span>setBoard</span>&#8203;<span>(int[][]&nbsp;board)</span></div>
<div>Import a board as a 9x9 array of integers with values ranging from 0-9</div>
<dl>
<dt>Parameters:</dt>
<dd><code>board</code> - 9x9 array of integers with values ranging from 0-9</dd>
</dl>
</li>
</ul>
</li>
