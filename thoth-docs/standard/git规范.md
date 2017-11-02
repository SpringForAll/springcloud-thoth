目标
  
[参考 ](http://www.thinksaas.cn/topics/0/400/400343.html)

生成 CHANGELOG.md  
识别不重要的提交  
在浏览 Git 历史时提供更多信息  
格式化提交信息  
恢复(Revert)  
信息头部
 - 必需`<type>`
 - 必需`<scope>`
 - `<subject>`文本  

<!-- more -->
 
信息主体  
信息尾部
 - 重大更改(Breaking changes)
 - 引用讨论(Referencing issues)

目标  
能够通过脚本生成 CHANGELOG.md  
能让 git bisect 过程忽略不重要的提交   
在浏览历史时提供更好的信息  
生成 CHANGELOG.md  
我们在 changeling 里使用这三个标志: new features, bug fixes, breaking changes. 在做一个发行版的时候，这个列表可以通过脚本来生成，通过关联这些相关的提交。 自然，你可以在实际发行之前编辑这些变更日志，但是这样子可以很容易的生成骨架。  
列出自最近一次发行以来所有的主体（提交信息第一行）  
`git log <last tag> HEAD —pretty=format:%s`  
在本次发行内的新功能  
`git log <last release> HEAD —grep feature`  
识别不重要的提交  
通常有很多的Formatting changes(adding/removing spaces/empty lines, indentation), missing semicolons, comments.所以当你查找一些变更的时候，你可以忽略这些提交 - 这些提交里面没有业务逻辑变更。  
当二分查找的时候，你可以忽视这些提交通过键入以下命令： 
`git bisect skip $(git rev-list —grep irrelevant <good place> HEAD)`  
在浏览历史时提供更多信息  
这将会增加一种“context”信息 看这些信息（通过查看最近的 angularjs 的提交信息）  
```
Fix small typo in docs widget (tutorial instructions)  
Fix test for scenario.Application - should remove old iframe  
docs - various doc fixes  
docs - stripping extra new lines  
Replaced double line break with single when text is fetched from Google  
Added support for properties in documentation
```
所有这些信息都尝试去表明更改的位置，但是它们没有使用公共的规范 再看看这些信息：  
```
fix comment stripping  
fixing broken links  
Bit of refactoring  
Check whether links do exist and throw exception  
Fix sitemap include (to work on case sensitive linux)
```
你能猜出这里面到底装了些什么？这些信息缺乏重点。 也许还有这些代码：docs, docs-parser, compiler, scenario-runner, ... 我知道，你可以通过查看哪些文件被改变来确定这个提交到底做了什么，但是这样子太麻烦了。 当查看 git 历史信息的时候我们可以看到大家都在努力保持一致，但是缺少一个公共规范。  
提交信息的格式化
```
<type>(<scope>):<subject>
 <BLANK LINE>
<body>
 <BLANK LINE>
<footer>
```
提交信息的任意一行都不能超过100个英文字符！这个能让信息在 github 上和 git 工具里更容易查看。  
恢复  
如果提交是用于恢复一个更早的提交，这个头部应该以“revert:”开始，接下来是被恢复提交的头部，在主体内应该写“This reverts commit <hash>.”，hash 的位置就是被恢复提交的 sha 值  
信息头部  
信息头部应该是包含改变的一条单行简要描述，包括一个类型，一个可选的范围和一个主题  
必需的<type> 这是用于说明提交的类型，下列是7个标志。  
```
feat: 新功能  
fix : 修补bug  
docs: 文档  
style: 格式化，缺少分号等  
refactor  
test: 增加缺少的测试  
chore 维护  
```
必需的<scope> 范围可以是任何制定的提交更改的地方。例如 $location, $browser, $compile, $rootScope, ngHref, ngClick, ngView等等 <subject>文本 这是变更的简单描述  
以动词开头，使用第一人称现在时，比如change，而不是changed或changes  
第一个字母小写  
结尾不加句号  
信息主体  
这部分是对本次提交的详细描述，可以分成多行  
使用第一人称现在时，比如使用 change 而不是 changed 或者 changes。  
说明代码变更的冬季，以及和以前代码的对比  
信息尾部  
只有两种情况： 不兼容变动 所有不兼容变动应该被列为不兼容变动块放在信息尾部，应该以“BREAKING CHANGE:”开始，后面是对变动的描述，以及变动的动机和迁移方法 引用讨论 如果当前提交是针对某个讨论，那么可以在尾部关闭这个讨论 Closes #234 或者同时关闭多个讨论 Closes #123, #245, #992