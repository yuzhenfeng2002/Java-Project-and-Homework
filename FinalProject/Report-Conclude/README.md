# nwafuCoursePaper
一个适用于西北农林科技大学科技类及IT类课程小论文排版的LaTeX模板。

Happy LaTeXing!~

## 注意

1. 本文档要求 TeXLive、MacTeX、MikTeX 不低于 2018 年的发行版，并且尽可能升级到最新，强烈建议使用TeXLive2019。

2. 使用说明见：**main.pdf**。

2. **不支持** [CTeX 套装](http://www.ctex.org/CTeXDownload)。

## 主要功能
- 方便科技类或IT类课程论文排版；
- 定制摘要、关键词、各级节标题、表格、页眉页脚等样式；
- 使用自己开发的[boxie.sty宏包](https://github.com/registor/boxiesty)实现终端窗口模拟、各类代码文本框和“注意”、“重要”、“技巧”和“警告”等文本框；
- 使用自己开发的[tikz-flowchart.sty宏包](https://github.com/registor/tikz-flowchart)实现流程图的绘制；
- 使用改造于`tikz-imagelabels`宏包的`tikz-imglabels`宏包实现插图的注解；
- 使用`floatrow`宏包控制图表浮动体；

## 注意
- 由于boxie.sty宏包需要使用fvextra.sty和lstlinebgrd.sty两个宏包处理间隔显示代码行颜色，请确保当前路径下有这两个宏包存在。

## 反馈
如果发现代码有问题，请按照以下步骤操作：

1. 将 TeX 发行版和宏包升级到最新，并且将模板升级到 Github 上最新版本，查看问题是否已经修复；
2. 在 [GitHub Issues](https://github.com/registor/csv2latextab/issues)中搜索该问题的关键词；
3. 在 [GitHub Issues](https://github.com/registor/csv2latextab/issues)中提出新 issue，并回答以下问题：
    - 使用了什么版本的 TeX Live / MacTeX / MikTeX ？
    - 具体的问题是什么？
    - 正确的结果应该是什么样的？
    - 是否应该附上相关源码或者截图？
4. 联系作者：西北农林科技大学信息工程学院耿楠
