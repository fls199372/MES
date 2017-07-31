package com.ewininfo.mes.module.menu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fulishuang on 2017/7/19.
 * 主界面的权限菜单
 */

public class Menu implements Serializable {

    /**
     * tsSysResourceId : fd4d681770b24cd0a67400dc13533cd8
     * name : 系统管理
     * url :
     * parentId : 5e11b089801b42d6bfbcfbf4d7045f96
     * level : 2
     * seq : 1
     * resourceType : 2
     * openType : 0
     * resourceIco : fa-cog
     * children : [{"tsSysResourceId":"9fd58f1ee28b40fc86faabb3fe668a98","name":"数据字典","url":"SysCodeType/sysCodeType.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":1,"resourceType":3,"openType":2,"resourceIco":"fa-","children":null},{"tsSysResourceId":"46bb3637d0e14aa6b1b41304b575dbd2","name":"系统参数","url":"SysParameter/sysParameter.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":2,"resourceType":3,"openType":2,"resourceIco":"fa-crosshairs","children":null},{"tsSysResourceId":"52fc5de097f24cbf99769c767bada0db","name":"资源信息","url":"sysResource/sysResource.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":3,"resourceType":3,"openType":2,"resourceIco":"fa-","children":null},{"tsSysResourceId":"6632268b8c8a46a9b126cfa7ca249b23","name":"提示信息","url":"SysMsg/sysMsg.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":4,"resourceType":3,"openType":1,"resourceIco":"fa-at","children":null},{"tsSysResourceId":"141a8702b78e4c0799fc684e4ab15231","name":"操作日志","url":"SysLog/sysLog.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":5,"resourceType":3,"openType":1,"resourceIco":"","children":null},{"tsSysResourceId":"cd161c9843024419aec430869c554545","name":"组件库","url":"../../src/demo/index.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":6,"resourceType":2,"openType":1,"resourceIco":"","children":null},{"tsSysResourceId":"79a621f1f4984cda8a9642758f6c1662","name":"页面组件","url":"../Base/Demo/Demo.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":7,"resourceType":3,"openType":1,"resourceIco":"fa-asterisk","children":null},{"tsSysResourceId":"7c95bcfd45d44791a6b10f11a341d608","name":"序列信息","url":"SysSequence/SysSeqIndex.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":9,"resourceType":2,"openType":2,"resourceIco":null,"children":null},{"tsSysResourceId":"69bdc74c32db4ffcae314d4869d1cfb2","name":"表字段信息","url":"../home/SysTable/sysTable.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":10,"resourceType":3,"openType":1,"resourceIco":null,"children":null},{"tsSysResourceId":"ae137853df0e46eb914955d07a3bc670","name":"表关联关系","url":"../home/SysTableRelation/sysTableRelation.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":11,"resourceType":3,"openType":1,"resourceIco":null,"children":null},{"tsSysResourceId":"fb45a5cf533d48749f0363698ddb1f5d","name":"业务控件库","url":"../home/SysComponents/sysComponents.html","parentId":"fd4d681770b24cd0a67400dc13533cd8","level":3,"seq":12,"resourceType":3,"openType":1,"resourceIco":null,"children":null}]
     */

    private String tsSysResourceId;
    private String name;
    private String url;
    private String parentId;
    private String level;
    private String seq;
    private String resourceType;
    private String openType;
    private String resourceIco;
    private List<ChildrenBean> children;

    public String getTsSysResourceId() {
        return tsSysResourceId;
    }

    public void setTsSysResourceId(String tsSysResourceId) {
        this.tsSysResourceId = tsSysResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getResourceIco() {
        return resourceIco;
    }

    public void setResourceIco(String resourceIco) {
        this.resourceIco = resourceIco;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean implements Serializable{
        /**
         * tsSysResourceId : 9fd58f1ee28b40fc86faabb3fe668a98
         * name : 数据字典
         * url : SysCodeType/sysCodeType.html
         * parentId : fd4d681770b24cd0a67400dc13533cd8
         * level : 3
         * seq : 1
         * resourceType : 3
         * openType : 2
         * resourceIco : fa-
         * children : null
         */

        private String tsSysResourceId;
        private String name;
        private String url;
        private String parentId;
        private int level;
        private int seq;
        private int resourceType;
        private int openType;
        private String resourceIco;
        private Object children;

        public String getTsSysResourceId() {
            return tsSysResourceId;
        }

        public void setTsSysResourceId(String tsSysResourceId) {
            this.tsSysResourceId = tsSysResourceId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public int getResourceType() {
            return resourceType;
        }

        public void setResourceType(int resourceType) {
            this.resourceType = resourceType;
        }

        public int getOpenType() {
            return openType;
        }

        public void setOpenType(int openType) {
            this.openType = openType;
        }

        public String getResourceIco() {
            return resourceIco;
        }

        public void setResourceIco(String resourceIco) {
            this.resourceIco = resourceIco;
        }

        public Object getChildren() {
            return children;
        }

        public void setChildren(Object children) {
            this.children = children;
        }
    }
}
