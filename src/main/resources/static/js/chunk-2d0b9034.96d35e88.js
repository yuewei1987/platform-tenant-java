(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0b9034"],{"30ab":function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-container",[n("el-header",[n("el-button",{attrs:{type:"primary",icon:"el-icon-news"},on:{click:function(t){return e.add()}}},[e._v("新增")])],1),n("el-main",[n("el-row",[n("el-col",[n("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:"",stripe:""}},[n("el-table-column",{attrs:{label:"Banner"},scopedSlots:e._u([{key:"default",fn:function(e){return[n("img",{staticStyle:{"max-height":"108px"},attrs:{src:e.row.url}})]}}])}),n("el-table-column",{attrs:{prop:"sortIndex",width:"80",label:"排序号"}}),n("el-table-column",{attrs:{label:"操作",width:"185"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{size:"mini",type:"primary",icon:"el-icon-edit"},on:{click:function(n){return e.editHandleClick(t.row.id)}}},[e._v("\n                                    编辑\n                                ")]),n("el-button",{attrs:{type:"danger",size:"mini",icon:"el-icon-delete"},on:{click:function(n){return e.deleteHandleClick(t.row.id)}}},[e._v("\n                                    删除\n                                ")])]}}])})],1)],1)],1),n("el-row",[n("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"total, prev, pager, next",total:e.countSize},on:{"current-change":e.handleCurrentChange,"update:currentPage":function(t){e.currentPage=t},"update:current-page":function(t){e.currentPage=t}}})],1)],1)],1)},r=[],i={data:function(){return{tableData:null,currentPage:1,countSize:0,pageSize:1}},mounted:function(){this.reload()},methods:{add:function(){this.$router.push({path:"/bannerManage/add",replace:!0})},editHandleClick:function(e){this.$router.push({path:"/bannerManage/add",query:{id:e},replace:!0})},handleCurrentChange:function(e){this.currentPage=e,this.reload()},reload:function(){var e=this;this.axios.get("/api/banner/page?page="+this.currentPage).then((function(t){e.tableData=t.data.data.content,e.currentPage=t.data.data.number+1,e.countSize=t.data.data.totalElements,e.pageSize=t.data.data.size})).catch((function(e){console.log(e)}))},deleteHandleClick:function(e){var t=this;this.$confirm("此操作将永久删除, 是否继续?","提示",{confirmButtonText:"确认",cancelButtonText:"取消",type:"warning"}).then((function(){t.axios.post("/api/banner/delete/"+e).then((function(e){"0"==e.data.state?t.reload():t.$message({type:"error",message:"删除失败"})})).catch((function(e){console.log(e)}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))}}},o=i,c=n("2877"),l=Object(c["a"])(o,a,r,!1,null,"f9385b60",null);t["default"]=l.exports}}]);
//# sourceMappingURL=chunk-2d0b9034.96d35e88.js.map