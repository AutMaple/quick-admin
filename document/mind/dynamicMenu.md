# 动态菜单实现思考的过程

1. 菜单跟角色挂钩，到时候只需要给予用户角色即可，方便管理
   - 需要一张角色与菜单的表

2. 菜单表该如何设计
   - level 字段：需要知道当前菜单是第几级菜单, 方便在代码中整理用户的菜单 => Map<level, menu> 
   - url 字段：表示当前菜单的功能
   - icon 字段：菜单图标
   - name 字段：菜单名
   - parentId 字段：父级菜单唯一编号