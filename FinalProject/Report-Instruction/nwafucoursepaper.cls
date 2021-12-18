%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% nwafucoursepaper - 西北农林科技大学科技类及IT类课程论文模板
%% nangeng@nwafu.edu.cn
%% 2019年10月
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{nwafucoursepaper}[October, 2019 LaTeX cls for NWAFU Course Papers]
\RequirePackage{kvoptions}
\DeclareDefaultOption{\PassOptionsToClass{\CurrentOption}{ctexart}}
\ProcessKeyvalOptions*
\PassOptionsToClass{zihao=-4}{ctexart}%,linespread=1.3889
\PassOptionsToClass{a4paper,scheme=chinese,space=auto,UTF8}{ctexart}
\LoadClass{ctexart}
%\LoadClass[a4paper,zihao=-4,scheme = chinese]{ctexart}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 其它需要的宏包
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\RequirePackage{etoolbox}% 为命令和环境打补丁的宏包
\RequirePackage{enumitem}% 调整列表格式
\RequirePackage{amsmath,mathrsfs,amsfonts} % 数学相关
\RequirePackage{booktabs}% 三线表格
\RequirePackage{colortbl}% 彩色表格
\RequirePackage{multirow,makecell}% 表格行合并，单元格处理
\RequirePackage{multicol}% 多栏
\RequirePackage{ulem}% 下划线
\RequirePackage[backend=biber,
                style=gb7714-2015,
                maxbibnames=99,% 著录所有作者
                maxcitenames=2,% 引用标注中最多显示2个作者
                mincitenames=1,% 3个及3个以上的作者截断为1个作者
                gbpub=false,
                gbnamefmt=familyahead,
                url=false,
                doi=false,
                isbn=false,
                gbfieldtype=true, % 输出学位论文标识
                ]{biblatex} % 参考文献

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% captions
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%\RequirePackage[labelsep=quad]{caption}
%\DeclareCaptionFont{kai}{\kaishu}
%\captionsetup[table]{belowskip=0pt,aboveskip=5pt,labelfont=kai,textfont=kai}
%\captionsetup[figure]{belowskip=0pt,aboveskip=5pt,format=hang,labelfont=kai,textfont=kai}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 浮动体增强宏包
%% 由于与minted宏包冲突，需要在boxie前载入
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\RequirePackage{floatrow}
\RequirePackage{subfig}
% 设置浮动体属性
\DeclareFloatVCode{beforefig}{\vspace{-4pt}}
\DeclareFloatVCode{beforetab}{\vspace{-6pt}}
\DeclareFloatVCode{afterfloat}{\vspace{4pt}}
\floatsetup{postcode=afterfloat}
\floatsetup[table]{captionskip=2pt, objectset=centering, margins=centering, precode=beforetab}
\floatsetup[figure]{captionskip=4pt, objectset=centering, margins=centering, precode=beforefig}

% 标题设置
\DeclareCaptionLabelSeparator{labsep}{\hspace{0.5em}}
\DeclareCaptionFont{hei}{\bfseries\heiti}
\captionsetup[table]{labelsep=labsep, belowskip=0pt,aboveskip=5pt,labelfont=hei,textfont=hei}
\captionsetup[figure]{labelsep=labsep, belowskip=0pt,aboveskip=5pt,format=hang,labelfont=hei,textfont=hei}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 命令行窗口及代码排版(自己开发的宏包)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\RequirePackage{boxie}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 插图标注宏包(修改为标注框可以换行)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\RequirePackage{tikz-imglabels}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 流程图宏包(自己开发)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\RequirePackage{tikz-flowchart}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 将列表项缩进方式改为首行缩进的中文格式
%% 需要enumitem宏包
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\setlist[description]{
    topsep=0pt,
    itemsep=0pt,
    partopsep=0pt,
    parsep=\parskip,
    labelindent=\parindent,
    labelwidth=1.3em,
    labelsep = .3em,
    itemindent=3.6em,
    leftmargin=!,
}

\setlist[enumerate]{
    topsep=0pt,
    itemsep=0pt,
    partopsep=0pt,
    parsep=\parskip,
    label={\arabic*)},
    labelindent=\parindent,
    labelwidth=1.3em,
    labelsep = .3em,
    itemindent=3.6em,
    leftmargin=!,
}
\setlist[itemize]{
    topsep=0pt,
    itemsep=0pt,
    partopsep=0pt,
    parsep=\parskip,
    labelindent=1.4em,
    labelwidth=1.3em,
    labelsep = .3em,
    itemindent=3em,
    leftmargin=!,
}
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% hyperref settings
%% 超链接设定
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\RequirePackage{hyperref}
\hypersetup{
  bookmarksnumbered,
  colorlinks,
  linkcolor={black},
  citecolor={black},
  urlcolor={black}
}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 交叉引用增强宏包(需在hyperref后引入)
%% \autoref命令
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% ===========设置引用标记名称============
\def\equationautorefname~#1\null{公式(#1)\null}%
\def\footnoteautorefname{脚注}%
\def\itemautorefname~#1\null{第#1项\null}%
\def\figureautorefname{图}%
\def\tableautorefname{表}%
%\def\partautorefname~#1\null{第#1部分\null}%
\def\appendixautorefname{附录}%
%\def\chapterautorefname~#1\null{第#1章\null}%
\def\sectionautorefname~#1\null{第 ~#1~ 节\null}%
\def\sectionautorefname~#1\null{第#1节\null}%
\def\subsectionautorefname~#1\null{第#1小节\null}%
\def\subsubsectionautorefname~#1\null{第#1小小节\null}%
\def\paragraphautorefname~#1\null{第#1段\null}%
\def\subparagraphautorefname~#1\null{第#1小段\null}%
\def\theoremautorefname{定理}%
\def\pageautorefname~#1\null{第~#1~页\null}%

\RequirePackage[nameinlink]{cleveref}
% 设置\cref章节引用格式
\crefformat{section}{第~#2#1#3~节}
\crefrangeformat{section}{第~#3#1#4~-~#5#2#6~节}
\crefformat{subsection}{第~#2#1#3~小节}
\crefrangeformat{subsection}{第~#3#1#4~-~#5#2#6~小节}
\crefformat{subsubsection}{第~#2#1#3~项}
\crefrangeformat{subsubsection}{第~#3#1#4~-~#5#2#6~项}
\crefname{table}{表}{表}
\creflabelformat{table}{#2#1#3~}
\crefrangelabelformat{table}{#3#1#4~-~#5#2#6~}
\crefname{figure}{图}{图}
\creflabelformat{figure}{#2#1#3~}
\crefrangelabelformat{figure}{#3#1#4~-~#5#2#6~}
\crefname{equation}{式}{式}
\creflabelformat{equation}{(#2#1#3)~}
\crefrangelabelformat{equation}{(#3#1#4)~-~(#5#2#6)~}
\crefrangeformat{equation}{公式~(#3#1#4) 至~(#5#2#6)}
\crefmultiformat{equation}{公式~(#2#1#3)}{ 和~(#2#1#3)}{, (#2#1#3)}{ 和~(#2#1#3)}
% 处理代码交叉引用格式
\renewcommand{\thecvcounter}{\thesection.\arabic{cvcounter}}
\crefname{cvcounter}{代码}{代码列表}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 书签宏包
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\RequirePackage[open,openlevel=0,atend]{bookmark}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% graphicx settings
%% 图片设定
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\RequirePackage{graphicx}
\graphicspath{{./figs/}{./figure/}{./figures/}{./image/}{./images/}{./graphics/}{./graphic/}{./pictures/}{./picture/}}


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% geometry settings
%% 页面设定
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\RequirePackage{geometry}
\geometry{
  textwidth=138mm,
  textheight=215mm,
  left=27mm,
  %% or
  %% inner=23mm,
  right=27mm,
  %% or
  %% outer=18mm,
  top=25.4mm, bottom=25.4mm,
  headheight=2.17cm,
  headsep=4mm,
  footskip=12mm,
  heightrounded,
}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 排版摘要
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\newcommand\makeabstract{  
  \nwafu@make@abstract@cn
}

\RequirePackage{environ}
% 内部命令
\newcommand\nwafu@label@abstract{摘要}
\newcommand\nwafu@label@keywords{关键词}
\newcommand\nwafu@label@keywordsep{; }
% 摘要环境与内容提取
\newcommand{\nwafu@@abstract}[1]{\long\gdef\nwafu@abstract{#1}}
\renewenvironment{abstract}{\Collect@Body\nwafu@@abstract}{}
% 定义列表提取命令
\def\nwafu@define@list#1#2{
  \define@key{nwafu}{#1}{\csname #1\endcsname{##1}}
  \expandafter\gdef\csname nwafu@#1\endcsname{}
  \expandafter\gdef\csname nwafu@#1@pdf\endcsname{}
  \expandafter\gdef\csname #1\endcsname##1{
    \@for\reserved@a:=##1\do{
      \expandafter\ifx\csname nwafu@#1\endcsname\@empty\else
        \expandafter\g@addto@macro\csname nwafu@#1\endcsname{%
          \ignorespaces #2}
        \expandafter\g@addto@macro\csname nwafu@#1@pdf\endcsname{,}
      \fi
      \expandafter\expandafter\expandafter\g@addto@macro%
        \expandafter\csname nwafu@#1\expandafter\endcsname\expandafter{\reserved@a}
    }
    \expandafter\gdef\csname nwafu@#1@pdf\endcsname{##1}
  }
}
\nwafu@define@list{keywords}{\nwafu@label@keywordsep}

% 关键字处理
%\newbox\nwafu@kw
%\newcommand{\nwafu@put@kw}[2]{%
%  \begingroup
%  \setbox\nwafu@kw=\hbox{#1}
%  \noindent\hangindent\wd\nwafu@kw\hangafter1
%  \box\nwafu@kw#2\par
%  \endgroup
%}
% 排版中文摘要
\newcommand\nwafu@make@abstract@cn{
  \noindent\rule{\textwidth}{.5pt}\\%[1ex]
  \begingroup    
    \setlength\parindent{2\ccwd}\songti\noindent
    {\heiti\zihao{-4} \makebox[\widthof{\nwafu@label@keywords}][s]{\nwafu@label@abstract}:}
    {\songti\zihao{5}\nwafu@abstract}
  \endgroup

  \vspace{2ex}

  \begingroup
    \setlength\parindent{2\ccwd}\songti\noindent
    {\heiti\zihao{-4} \nwafu@label@keywords{}:}
    {\songti\zihao{5}\nwafu@keywords}
    %\nwafu@put@kw{\heiti\zihao{-4}\nwafu@label@keywords{}：}{\songti\zihao{5}\nwafu@keywords}    
  \endgroup\\[-1ex]
  \rule{\textwidth}{.5pt}
}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% titlesec
%% 标题设定
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% \RequirePackage{titlesec}
\ctexset{
  section = {
    format+ = \zihao{4} \heiti \raggedright,
    %name = {,、},
    %number = \chinese{section},
    beforeskip = 1.0ex plus 0.2ex minus .2ex,
    afterskip = 1.0ex plus 0.2ex minus .2ex,
    aftername = \hspace{0.5\ccwd}
  },
  subsection = {
    format+ = \zihao{-4} \heiti \raggedright,
    % name={\thesubsection、},
    %name = {,、},
    %number = \arabic{subsection},
    beforeskip = 1.0ex plus 0.2ex minus .2ex,
    afterskip = 1.0ex plus 0.2ex minus .2ex,
    aftername = \hspace{0.5\ccwd},
    %indent=2\ccwd,%
  },
  subsubsection = {
    name={,.},%
    number=\arabic{subsubsection},%
    format+ = \zihao{-4} \heiti,
    beforeskip = 1.0ex plus 0.2ex minus .2ex,
    afterskip = 1.0ex plus 0.2ex minus .2ex,
    aftername = \hspace{0.5\ccwd},
    indent=2\ccwd,%
  }
}
%\renewenvironment{abstract}{\noindent\rule{\textwidth}{.5pt}\\[2ex] \centering\begin{minipage}{.97\textwidth}{\zihao{-4}\sffamily\bfseries\abstractname}\\}
%{\par\noindent\end{minipage}\\[2ex] \rule{\textwidth}{.5pt}}

% %% section
% \titleformat{\section}
% [hang]
% {\sffamily}
% {\centering\zihao{4}\bfseries \chinese{section}、}%\thesection
% {1pt}
% {\zihao{4}\bfseries}

% %% subsection
% \titleformat{\subsection}
% [hang]
% {\sffamily}
% {\zihao{4}\bfseries\thesubsection\enspace}
% {1pt}
% {\zihao{4}\bfseries\filright}

% %% subsubsection
% \titleformat{\subsubsection}
% [hang]
% {\sffamily}
% {\zihao{-4}\bfseries\thesubsubsection\enspace}
% {1pt}
% {\zihao{-4}\bfseries\filright}

% \titlespacing{\section}{0pt}{2.5ex plus 1ex minus .2ex}{1.3ex plus .2ex}
% \titlespacing{\subsection}{0pt}{\parskip}{-\parskip}
% \titlespacing{\subsubsection}{0pt}{\parskip}{-\parskip}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% head and foot
%% 页眉页脚
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\RequirePackage{fancyhdr}

\pagestyle{fancy}
\fancyhf{}
\renewcommand\headrulewidth{.5pt}
\renewcommand\footrulewidth{0pt}
%\futurelet\TMPheadrule\def\headrule{{\color{violet}\TMPheadrule}}

\renewcommand{\sectionmark}[1]{\markright{#1}}

\fancyhead[L]{\kaishu{\rightmark}}%\rightmark
\fancyhead[R]{\thepage}

% 中文行距调整
\RequirePackage{zhlineskip}

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 重定义强调字体的代码，
%% 解决默认强调字体是italic，中文用楷体代替，
%% 在此设置为加粗，注意需要使用etoolbox宏包
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\makeatletter
\let\origemph\emph
\newcommand*\emphfont{\normalfont\bfseries}
\DeclareTextFontCommand\@textemph{\emphfont}
\newcommand\textem[1]{%
  \ifdefstrequal{\f@series}{\bfdefault}
    {\@textemph{\CTEXunderline{#1}}}
    {\@textemph{#1}}%
}
\RenewDocumentCommand\emph{s o m}{%
  \IfBooleanTF{#1}
    {\textem{#3}}
    {\IfNoValueTF{#2}
      {\textem{#3}\index{#3}}
      {\textem{#3}\index{#2}}%
     }%
}
\makeatother

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% 解决中英文混排文字超出边界问题
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
\sloppy%

\endinput
%% End of file `nwafucoursepaper.cls'.
