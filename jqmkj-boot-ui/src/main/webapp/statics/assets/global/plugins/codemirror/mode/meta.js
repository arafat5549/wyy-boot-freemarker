// CodeMirror, copyright (c) by Marijn Haverbeke and others
// Distributed under an MIT license: http://codemirror.net/LICENSE

(function (mod) {
    if (typeof exports == "object" && typeof module == "object") // CommonJS
        mod(require("../lib/codemirror"));
    else if (typeof define == "function" && define.amd) // AMD
        define(["../lib/codemirror"], mod);
    else // Plain browser env
        mod(CodeMirror);
})(function (CodeMirror) {
    "use strict";

    CodeMirror.modeInfo = [
        {name: "APL", mime: "text/apl", mode: "apl", ext: ["dyalog", "apl"]},
        {
            name: "PGP",
            mimes: ["application/pgp", "application/pgp-keys", "application/pgp-signature"],
            mode: "asciiarmor",
            ext: ["pgp"]
        },
        {name: "ASN.1", mime: "text/plugins.ligerui-ttcn-asn", mode: "asn.1", ext: ["asn", "asn1"]},
        {name: "Asterisk", mime: "text/plugins.ligerui-asterisk", mode: "asterisk", file: /^extensions\.conf$/i},
        {name: "Brainfuck", mime: "text/plugins.ligerui-brainfuck", mode: "brainfuck", ext: ["b", "bf"]},
        {name: "C", mime: "text/plugins.ligerui-csrc", mode: "clike", ext: ["c", "h"]},
        {
            name: "C++",
            mime: "text/plugins.ligerui-c++src",
            mode: "clike",
            ext: ["cpp", "c++", "cc", "cxx", "hpp", "h++", "hh", "hxx"],
            alias: ["cpp"]
        },
        {name: "Cobol", mime: "text/plugins.ligerui-cobol", mode: "cobol", ext: ["cob", "cpy"]},
        {name: "C#", mime: "text/plugins.ligerui-csharp", mode: "clike", ext: ["cs"], alias: ["csharp"]},
        {name: "Clojure", mime: "text/plugins.ligerui-clojure", mode: "clojure", ext: ["clj"]},
        {name: "CMake", mime: "text/plugins.ligerui-cmake", mode: "cmake", ext: ["cmake", "cmake.in"], file: /^CMakeLists.txt$/},
        {
            name: "CoffeeScript",
            mime: "text/plugins.ligerui-coffeescript",
            mode: "coffeescript",
            ext: ["coffee"],
            alias: ["coffee", "coffee-script"]
        },
        {
            name: "Common Lisp",
            mime: "text/plugins.ligerui-common-lisp",
            mode: "commonlisp",
            ext: ["cl", "lisp", "el"],
            alias: ["lisp"]
        },
        {name: "Cypher", mime: "application/plugins.ligerui-cypher-query", mode: "cypher", ext: ["cyp", "cypher"]},
        {name: "Cython", mime: "text/plugins.ligerui-cython", mode: "python", ext: ["pyx", "pxd", "pxi"]},
        {name: "CSS", mime: "text/css", mode: "css", ext: ["css"]},
        {name: "CQL", mime: "text/plugins.ligerui-cassandra", mode: "sql", ext: ["cql"]},
        {name: "D", mime: "text/plugins.ligerui-d", mode: "d", ext: ["d"]},
        {name: "Dart", mimes: ["application/dart", "text/plugins.ligerui-dart"], mode: "dart", ext: ["dart"]},
        {name: "diff", mime: "text/plugins.ligerui-diff", mode: "diff", ext: ["diff", "patch"]},
        {name: "Django", mime: "text/plugins.ligerui-django", mode: "django"},
        {name: "Dockerfile", mime: "text/plugins.ligerui-dockerfile", mode: "dockerfile", file: /^Dockerfile$/},
        {name: "DTD", mime: "application/xml-dtd", mode: "dtd", ext: ["dtd"]},
        {name: "Dylan", mime: "text/plugins.ligerui-dylan", mode: "dylan", ext: ["dylan", "dyl", "intr"]},
        {name: "EBNF", mime: "text/plugins.ligerui-ebnf", mode: "ebnf"},
        {name: "ECL", mime: "text/plugins.ligerui-ecl", mode: "ecl", ext: ["ecl"]},
        {name: "Eiffel", mime: "text/plugins.ligerui-eiffel", mode: "eiffel", ext: ["e"]},
        {name: "Elm", mime: "text/plugins.ligerui-elm", mode: "elm", ext: ["elm"]},
        {name: "Embedded Javascript", mime: "application/plugins.ligerui-ejs", mode: "htmlembedded", ext: ["ejs"]},
        {name: "Embedded Ruby", mime: "application/plugins.ligerui-erb", mode: "htmlembedded", ext: ["erb"]},
        {name: "Erlang", mime: "text/plugins.ligerui-erlang", mode: "erlang", ext: ["erl"]},
        {name: "Factor", mime: "text/plugins.ligerui-factor", mode: "factor", ext: ["factor"]},
        {name: "Forth", mime: "text/plugins.ligerui-forth", mode: "forth", ext: ["forth", "fth", "4th"]},
        {name: "Fortran", mime: "text/plugins.ligerui-fortran", mode: "fortran", ext: ["f", "for", "f77", "f90"]},
        {name: "F#", mime: "text/plugins.ligerui-fsharp", mode: "mllike", ext: ["fs"], alias: ["fsharp"]},
        {name: "Gas", mime: "text/plugins.ligerui-gas", mode: "gas", ext: ["s"]},
        {name: "Gherkin", mime: "text/plugins.ligerui-feature", mode: "gherkin", ext: ["feature"]},
        {
            name: "GitHub Flavored Markdown",
            mime: "text/plugins.ligerui-gfm",
            mode: "gfm",
            file: /^(readme|contributing|history).md$/i
        },
        {name: "Go", mime: "text/plugins.ligerui-go", mode: "go", ext: ["go"]},
        {name: "Groovy", mime: "text/plugins.ligerui-groovy", mode: "groovy", ext: ["groovy"]},
        {name: "HAML", mime: "text/plugins.ligerui-haml", mode: "haml", ext: ["haml"]},
        {name: "Haskell", mime: "text/plugins.ligerui-haskell", mode: "haskell", ext: ["hs"]},
        {name: "Haxe", mime: "text/plugins.ligerui-haxe", mode: "haxe", ext: ["hx"]},
        {name: "HXML", mime: "text/plugins.ligerui-hxml", mode: "haxe", ext: ["hxml"]},
        {name: "ASP.NET", mime: "application/plugins.ligerui-aspx", mode: "htmlembedded", ext: ["aspx"], alias: ["asp", "aspx"]},
        {name: "HTML", mime: "text/html", mode: "htmlmixed", ext: ["html", "htm"], alias: ["xhtml"]},
        {name: "HTTP", mime: "message/http", mode: "http"},
        {name: "IDL", mime: "text/plugins.ligerui-idl", mode: "idl", ext: ["pro"]},
        {name: "Jade", mime: "text/plugins.ligerui-jade", mode: "jade", ext: ["jade"]},
        {name: "Java", mime: "text/plugins.ligerui-java", mode: "clike", ext: ["java"]},
        {name: "Java Server Pages", mime: "application/plugins.ligerui-jsp", mode: "htmlembedded", ext: ["jsp"], alias: ["jsp"]},
        {
            name: "JavaScript",
            mimes: ["text/javascript", "text/ecmascript", "application/javascript", "application/plugins.ligerui-javascript", "application/ecmascript"],
            mode: "javascript",
            ext: ["js"],
            alias: ["ecmascript", "js", "node"]
        },
        {
            name: "JSON",
            mimes: ["application/json", "application/plugins.ligerui-json"],
            mode: "javascript",
            ext: ["json", "map"],
            alias: ["json5"]
        },
        {name: "JSON-LD", mime: "application/ld+json", mode: "javascript", ext: ["jsonld"], alias: ["jsonld"]},
        {name: "Jinja2", mime: "null", mode: "jinja2"},
        {name: "Julia", mime: "text/plugins.ligerui-julia", mode: "julia", ext: ["jl"]},
        {name: "Kotlin", mime: "text/plugins.ligerui-kotlin", mode: "kotlin", ext: ["kt"]},
        {name: "LESS", mime: "text/plugins.ligerui-less", mode: "css", ext: ["less"]},
        {name: "LiveScript", mime: "text/plugins.ligerui-livescript", mode: "livescript", ext: ["ls"], alias: ["ls"]},
        {name: "Lua", mime: "text/plugins.ligerui-lua", mode: "lua", ext: ["lua"]},
        {name: "Markdown", mime: "text/plugins.ligerui-markdown", mode: "markdown", ext: ["markdown", "md", "mkd"]},
        {name: "mIRC", mime: "text/mirc", mode: "mirc"},
        {name: "MariaDB SQL", mime: "text/plugins.ligerui-mariadb", mode: "sql"},
        {name: "Mathematica", mime: "text/plugins.ligerui-mathematica", mode: "mathematica", ext: ["m", "nb"]},
        {name: "Modelica", mime: "text/plugins.ligerui-modelica", mode: "modelica", ext: ["mo"]},
        {name: "MUMPS", mime: "text/plugins.ligerui-mumps", mode: "mumps"},
        {name: "MS SQL", mime: "text/plugins.ligerui-mssql", mode: "sql"},
        {name: "MySQL", mime: "text/plugins.ligerui-mysql", mode: "sql"},
        {name: "Nginx", mime: "text/plugins.ligerui-nginx-conf", mode: "nginx", file: /nginx.*\.conf$/i},
        {name: "NTriples", mime: "text/n-triples", mode: "ntriples", ext: ["nt"]},
        {name: "Objective C", mime: "text/plugins.ligerui-objectivec", mode: "clike", ext: ["m", "mm"]},
        {name: "OCaml", mime: "text/plugins.ligerui-ocaml", mode: "mllike", ext: ["ml", "mli", "mll", "mly"]},
        {name: "Octave", mime: "text/plugins.ligerui-octave", mode: "octave", ext: ["m"]},
        {name: "Pascal", mime: "text/plugins.ligerui-pascal", mode: "pascal", ext: ["p", "pas"]},
        {name: "PEG.js", mime: "null", mode: "pegjs", ext: ["jsonld"]},
        {name: "Perl", mime: "text/plugins.ligerui-perl", mode: "perl", ext: ["pl", "pm"]},
        {name: "PHP", mime: "application/plugins.ligerui-httpd-php", mode: "php", ext: ["php", "php3", "php4", "php5", "phtml"]},
        {name: "Pig", mime: "text/plugins.ligerui-pig", mode: "pig", ext: ["pig"]},
        {name: "Plain Text", mime: "text/plain", mode: "null", ext: ["txt", "text", "conf", "def", "list", "log"]},
        {name: "PLSQL", mime: "text/plugins.ligerui-plsql", mode: "sql", ext: ["pls"]},
        {
            name: "Properties files",
            mime: "text/plugins.ligerui-properties",
            mode: "properties",
            ext: ["properties", "ini", "in"],
            alias: ["ini", "properties"]
        },
        {name: "Python", mime: "text/plugins.ligerui-python", mode: "python", ext: ["py", "pyw"]},
        {name: "Puppet", mime: "text/plugins.ligerui-puppet", mode: "puppet", ext: ["pp"]},
        {name: "Q", mime: "text/plugins.ligerui-q", mode: "q", ext: ["q"]},
        {name: "R", mime: "text/plugins.ligerui-rsrc", mode: "r", ext: ["r"], alias: ["rscript"]},
        {name: "reStructuredText", mime: "text/plugins.ligerui-rst", mode: "rst", ext: ["rst"], alias: ["rst"]},
        {name: "RPM Changes", mime: "text/plugins.ligerui-rpm-changes", mode: "rpm"},
        {name: "RPM Spec", mime: "text/plugins.ligerui-rpm-spec", mode: "rpm", ext: ["spec"]},
        {
            name: "Ruby",
            mime: "text/plugins.ligerui-ruby",
            mode: "ruby",
            ext: ["rb"],
            alias: ["jruby", "macruby", "rake", "rb", "rbx"]
        },
        {name: "Rust", mime: "text/plugins.ligerui-rustsrc", mode: "rust", ext: ["rs"]},
        {name: "Sass", mime: "text/plugins.ligerui-sass", mode: "sass", ext: ["sass"]},
        {name: "Scala", mime: "text/plugins.ligerui-scala", mode: "clike", ext: ["scala"]},
        {name: "Scheme", mime: "text/plugins.ligerui-scheme", mode: "scheme", ext: ["scm", "ss"]},
        {name: "SCSS", mime: "text/plugins.ligerui-scss", mode: "css", ext: ["scss"]},
        {name: "Shell", mime: "text/plugins.ligerui-sh", mode: "shell", ext: ["sh", "ksh", "bash"], alias: ["bash", "sh", "zsh"]},
        {name: "Sieve", mime: "application/sieve", mode: "sieve", ext: ["siv", "sieve"]},
        {name: "Slim", mimes: ["text/plugins.ligerui-slim", "application/plugins.ligerui-slim"], mode: "slim", ext: ["slim"]},
        {name: "Smalltalk", mime: "text/plugins.ligerui-stsrc", mode: "smalltalk", ext: ["st"]},
        {name: "Smarty", mime: "text/plugins.ligerui-smarty", mode: "smarty", ext: ["tpl"]},
        {name: "Solr", mime: "text/plugins.ligerui-solr", mode: "solr"},
        {name: "Soy", mime: "text/plugins.ligerui-soy", mode: "soy", ext: ["soy"], alias: ["closure template"]},
        {name: "SPARQL", mime: "application/sparql-query", mode: "sparql", ext: ["rq", "sparql"], alias: ["sparul"]},
        {name: "Spreadsheet", mime: "text/plugins.ligerui-spreadsheet", mode: "spreadsheet", alias: ["excel", "formula"]},
        {name: "SQL", mime: "text/plugins.ligerui-sql", mode: "sql", ext: ["sql"]},
        {name: "Squirrel", mime: "text/plugins.ligerui-squirrel", mode: "clike", ext: ["nut"]},
        {name: "Swift", mime: "text/plugins.ligerui-swift", mode: "swift", ext: ["swift"]},
        {name: "MariaDB", mime: "text/plugins.ligerui-mariadb", mode: "sql"},
        {name: "sTeX", mime: "text/plugins.ligerui-stex", mode: "stex"},
        {name: "LaTeX", mime: "text/plugins.ligerui-latex", mode: "stex", ext: ["text", "ltx"], alias: ["tex"]},
        {name: "SystemVerilog", mime: "text/plugins.ligerui-systemverilog", mode: "verilog", ext: ["v"]},
        {name: "Tcl", mime: "text/plugins.ligerui-tcl", mode: "tcl", ext: ["tcl"]},
        {name: "Textile", mime: "text/plugins.ligerui-textile", mode: "textile", ext: ["textile"]},
        {name: "TiddlyWiki ", mime: "text/plugins.ligerui-tiddlywiki", mode: "tiddlywiki"},
        {name: "Tiki wiki", mime: "text/tiki", mode: "tiki"},
        {name: "TOML", mime: "text/plugins.ligerui-toml", mode: "toml", ext: ["toml"]},
        {name: "Tornado", mime: "text/plugins.ligerui-tornado", mode: "tornado"},
        {name: "troff", mime: "troff", mode: "troff", ext: ["1", "2", "3", "4", "5", "6", "7", "8", "9"]},
        {name: "TTCN", mime: "text/plugins.ligerui-ttcn", mode: "ttcn", ext: ["ttcn", "ttcn3", "ttcnpp"]},
        {name: "TTCN_CFG", mime: "text/plugins.ligerui-ttcn-cfg", mode: "ttcn-cfg", ext: ["cfg"]},
        {name: "Turtle", mime: "text/turtle", mode: "turtle", ext: ["ttl"]},
        {name: "TypeScript", mime: "application/typescript", mode: "javascript", ext: ["ts"], alias: ["ts"]},
        {name: "Twig", mime: "text/plugins.ligerui-twig", mode: "twig"},
        {name: "VB.NET", mime: "text/plugins.ligerui-vb", mode: "vb", ext: ["vb"]},
        {name: "VBScript", mime: "text/vbscript", mode: "vbscript", ext: ["vbs"]},
        {name: "Velocity", mime: "text/velocity", mode: "velocity", ext: ["vtl"]},
        {name: "Verilog", mime: "text/plugins.ligerui-verilog", mode: "verilog", ext: ["v"]},
        {name: "VHDL", mime: "text/plugins.ligerui-vhdl", mode: "vhdl", ext: ["vhd", "vhdl"]},
        {
            name: "XML",
            mimes: ["application/xml", "text/xml"],
            mode: "xml",
            ext: ["xml", "xsl", "xsd"],
            alias: ["rss", "wsdl", "xsd"]
        },
        {name: "XQuery", mime: "application/xquery", mode: "xquery", ext: ["xy", "xquery"]},
        {name: "YAML", mime: "text/plugins.ligerui-yaml", mode: "yaml", ext: ["yaml", "yml"], alias: ["yml"]},
        {name: "Z80", mime: "text/plugins.ligerui-z80", mode: "z80", ext: ["z80"]}
    ];
    // Ensure all modes have a mime property for backwards compatibility
    for (var i = 0; i < CodeMirror.modeInfo.length; i++) {
        var info = CodeMirror.modeInfo[i];
        if (info.mimes) info.mime = info.mimes[0];
    }

    CodeMirror.findModeByMIME = function (mime) {
        mime = mime.toLowerCase();
        for (var i = 0; i < CodeMirror.modeInfo.length; i++) {
            var info = CodeMirror.modeInfo[i];
            if (info.mime == mime) return info;
            if (info.mimes) for (var j = 0; j < info.mimes.length; j++)
                if (info.mimes[j] == mime) return info;
        }
    };

    CodeMirror.findModeByExtension = function (ext) {
        for (var i = 0; i < CodeMirror.modeInfo.length; i++) {
            var info = CodeMirror.modeInfo[i];
            if (info.ext) for (var j = 0; j < info.ext.length; j++)
                if (info.ext[j] == ext) return info;
        }
    };

    CodeMirror.findModeByFileName = function (filename) {
        for (var i = 0; i < CodeMirror.modeInfo.length; i++) {
            var info = CodeMirror.modeInfo[i];
            if (info.file && info.file.test(filename)) return info;
        }
        var dot = filename.lastIndexOf(".");
        var ext = dot > -1 && filename.substring(dot + 1, filename.length);
        if (ext) return CodeMirror.findModeByExtension(ext);
    };

    CodeMirror.findModeByName = function (name) {
        name = name.toLowerCase();
        for (var i = 0; i < CodeMirror.modeInfo.length; i++) {
            var info = CodeMirror.modeInfo[i];
            if (info.name.toLowerCase() == name) return info;
            if (info.alias) for (var j = 0; j < info.alias.length; j++)
                if (info.alias[j].toLowerCase() == name) return info;
        }
    };
});
