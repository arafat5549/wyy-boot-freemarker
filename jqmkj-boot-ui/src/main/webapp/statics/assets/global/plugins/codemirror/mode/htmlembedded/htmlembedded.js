// CodeMirror, copyright (c) by Marijn Haverbeke and others
// Distributed under an MIT license: http://codemirror.net/LICENSE

(function (mod) {
    if (typeof exports == "object" && typeof module == "object") // CommonJS
        mod(require("../../lib/codemirror"), require("../htmlmixed/htmlmixed"),
            require("../../addon/mode/multiplex"));
    else if (typeof define == "function" && define.amd) // AMD
        define(["../../lib/codemirror", "../htmlmixed/htmlmixed",
            "../../addon/mode/multiplex"], mod);
    else // Plain browser env
        mod(CodeMirror);
})(function (CodeMirror) {
    "use strict";

    CodeMirror.defineMode("htmlembedded", function (config, parserConfig) {
        return CodeMirror.multiplexingMode(CodeMirror.getMode(config, "htmlmixed"), {
            open: parserConfig.open || parserConfig.scriptStartRegex || "<%",
            close: parserConfig.close || parserConfig.scriptEndRegex || "%>",
            mode: CodeMirror.getMode(config, parserConfig.scriptingModeSpec)
        });
    }, "htmlmixed");

    CodeMirror.defineMIME("application/plugins.ligerui-ejs", {name: "htmlembedded", scriptingModeSpec: "javascript"});
    CodeMirror.defineMIME("application/plugins.ligerui-aspx", {name: "htmlembedded", scriptingModeSpec: "text/plugins.ligerui-csharp"});
    CodeMirror.defineMIME("application/plugins.ligerui-jsp", {name: "htmlembedded", scriptingModeSpec: "text/plugins.ligerui-java"});
    CodeMirror.defineMIME("application/plugins.ligerui-erb", {name: "htmlembedded", scriptingModeSpec: "ruby"});
});
