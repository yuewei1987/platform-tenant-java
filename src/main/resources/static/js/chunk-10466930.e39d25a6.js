(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-10466930"],{"11e9":function(e,t,o){var r=o("52a7"),n=o("4630"),i=o("6821"),a=o("6a99"),c=o("69a8"),l=o("c69a"),u=Object.getOwnPropertyDescriptor;t.f=o("9e1e")?u:function(e,t){if(e=i(e),t=a(t,!0),l)try{return u(e,t)}catch(o){}if(c(e,t))return n(!r.f.call(e,t),e[t])}},"2a52":function(e,t,o){"use strict";var r=o("383d"),n=o.n(r);n.a},"2f48":function(e,t,o){"use strict";var r=o("487c"),n=o.n(r);n.a},"383d":function(e,t,o){},"454f":function(e,t,o){o("46a7");var r=o("584a").Object;e.exports=function(e,t,o){return r.defineProperty(e,t,o)}},"456d":function(e,t,o){var r=o("4bf8"),n=o("0d58");o("5eda")("keys",(function(){return function(e){return n(r(e))}}))},"46a7":function(e,t,o){var r=o("63b6");r(r.S+r.F*!o("8e60"),"Object",{defineProperty:o("d9f6").f})},"487c":function(e,t,o){},"5eda":function(e,t,o){var r=o("5ca1"),n=o("8378"),i=o("79e5");e.exports=function(e,t){var o=(n.Object||{})[e]||Object[e],a={};a[e]=t(o),r(r.S+r.F*i((function(){o(1)})),"Object",a)}},"85f2":function(e,t,o){e.exports=o("454f")},"8e6e":function(e,t,o){var r=o("5ca1"),n=o("990b"),i=o("6821"),a=o("11e9"),c=o("f1ae");r(r.S,"Object",{getOwnPropertyDescriptors:function(e){var t,o,r=i(e),l=a.f,u=n(r),s={},f=0;while(u.length>f)o=l(r,t=u[f++]),void 0!==o&&c(s,t,o);return s}})},9093:function(e,t,o){var r=o("ce10"),n=o("e11e").concat("length","prototype");t.f=Object.getOwnPropertyNames||function(e){return r(e,n)}},"990b":function(e,t,o){var r=o("9093"),n=o("2621"),i=o("cb7c"),a=o("7726").Reflect;e.exports=a&&a.ownKeys||function(e){var t=r.f(i(e)),o=n.f;return o?t.concat(o(e)):t}},bd86:function(e,t,o){"use strict";o.d(t,"a",(function(){return i}));var r=o("85f2"),n=o.n(r);function i(e,t,o){return t in e?n()(e,t,{value:o,enumerable:!0,configurable:!0,writable:!0}):e[t]=o,e}},f1ae:function(e,t,o){"use strict";var r=o("86cc"),n=o("4630");e.exports=function(e,t,o){t in e?r.f(e,t,n(0,o)):e[t]=o}},fb2a:function(e,t,o){"use strict";o.r(t);var r=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("el-container",[o("el-header",[o("el-button",{attrs:{type:"primary",icon:"el-icon-news"},on:{click:function(t){return e.submitForm("ruleForm")}}},[e._v("保存")]),o("el-button",{attrs:{type:"info",icon:"el-icon-back"},on:{click:function(t){return e.back()}}},[e._v("返回")])],1),o("el-main",[o("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"200px"}},[o("el-form-item",{attrs:{label:"分类名称",prop:"name"}},[o("el-input",{attrs:{maxlength:"4",label:"请输入排序号"},model:{value:e.ruleForm.name,callback:function(t){e.$set(e.ruleForm,"name",t)},expression:"ruleForm.name"}})],1),o("el-form-item",{attrs:{label:"分类图标",prop:"url"}},[o("el-upload",{staticClass:"upload-demo",attrs:{action:"",drag:"",accept:"image/jpeg,image/gif,image/png","auto-upload":!1,"show-file-list":!1,"on-change":e.changeUpload}},[o("img",{staticClass:"pic",attrs:{src:e.ruleForm.url}}),o("i",{staticClass:"el-icon-upload"}),o("div",{staticClass:"el-upload__text"},[e._v("点击上传")])])],1),o("el-form-item",{attrs:{label:"排序号",prop:"sortIndex"}},[o("el-input-number",{attrs:{min:1,max:99,label:"请输入排序号"},model:{value:e.ruleForm.sortIndex,callback:function(t){e.$set(e.ruleForm,"sortIndex",t)},expression:"ruleForm.sortIndex"}})],1)],1)],1),o("el-dialog",{attrs:{title:"剪裁图片",visible:e.dialogVisible,"append-to-body":""},on:{"update:visible":function(t){e.dialogVisible=t}}},[o("div",{staticClass:"cropper-content"},[o("div",{staticClass:"cropper",staticStyle:{"text-align":"center"}},[o("vueCropper",{ref:"cropper",attrs:{img:e.option.img,outputSize:e.option.size,outputType:e.option.outputType,info:!0,full:e.option.full,canMove:e.option.canMove,canMoveBox:e.option.canMoveBox,original:e.option.original,autoCrop:e.option.autoCrop,fixed:e.option.fixed,fixedNumber:e.option.fixedNumber,centerBox:e.option.centerBox,infoTrue:e.option.infoTrue,fixedBox:e.option.fixedBox},on:{realTime:e.realTime}})],1)]),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取消")]),o("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.finish}},[e._v("上传")])],1)])],1)},n=[],i=(o("8e6e"),o("ac6a"),o("456d"),o("bd86"));o("7f7f");function a(e,t){var o=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),o.push.apply(o,r)}return o}function c(e){for(var t=1;t<arguments.length;t++){var o=null!=arguments[t]?arguments[t]:{};t%2?a(Object(o),!0).forEach((function(t){Object(i["a"])(e,t,o[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(o)):a(Object(o)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(o,t))}))}return e}var l={data:function(){return{previews:{},dialogVisible:!1,loading:!1,ruleForm:{id:"",sortIndex:"",fid:"",name:"",url:""},option:{img:"",info:!0,outputSize:1,outputType:"png",canScale:!0,autoCrop:!0,fixedBox:!1,fixed:!0,fixedNumber:[1,1],full:!0,canMoveBox:!0,original:!1,centerBox:!0,infoTrue:!0},rules:{name:[{required:!0,message:"请输入分类名称",trigger:"blur"}],sortIndex:[{required:!0,message:"请输入排序号",trigger:"blur"}],url:[{required:!0,message:"请上传分类图标",trigger:"blur"}]}}},mounted:function(){var e=this;void 0!=this.$route.query.id&&(this.ruleForm.id=this.$route.query.id,this.axios.get("/api/classify/get/"+this.$route.query.id).then((function(t){e.ruleForm.sortIndex=t.data.data.sortIndex,e.ruleForm.url=t.data.data.url,e.ruleForm.name=t.data.data.name,e.ruleForm.fid=t.data.data.fid})).catch((function(e){console.log(e)})))},methods:{changeUpload:function(e){var t="image/jpeg"===e.raw.type||"image/png"===e.raw.type||"image/gif"===e.raw.type;if(!t)return this.$message({type:"info",message:"上传文件只能是图片格式!"}),!1;var o=new FileReader;o.readAsDataURL(e.raw);var r=this;o.onload=function(){r.dialogVisible=!0,r.option.img=this.result}},realTime:function(e){this.previews=e},finish:function(){var e=this;this.$refs.cropper.getCropBlob((function(t){e.loading=!0;var o=new FormData;o.append("file",t);var r={headers:{"Content-Type":"multipart/form-data"}};e.axios.post("/api/upload/file",o,r).then((function(t){0==t.data.state?(e.dialogVisible=!1,console.log(t.data.data),e.ruleForm.url=t.data.data,e.loading=!1):(e.loading=!1,e.$message({type:"info",message:"上传出错"}))}))}))},submitForm:function(e){var t=this;this.$refs[e].validate((function(e){if(!e)return console.log("error submit!!"),!1;t.axios.post("/api/classify/save",c({},t.ruleForm)).then((function(e){"0"==e.data.state?t.back():t.$message({type:"info",message:"保存失败"})})).catch((function(e){console.log(e)}))}))},back:function(){this.$router.push({path:"/typeManage/index",replace:!0})}}},u=l,s=(o("2f48"),o("2a52"),o("2877")),f=Object(s["a"])(u,r,n,!1,null,"3329c94b",null);t["default"]=f.exports}}]);
//# sourceMappingURL=chunk-10466930.e39d25a6.js.map