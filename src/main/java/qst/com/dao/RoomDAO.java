package qst.com.dao;

import qst.com.bean.Room;
import qst.com.util.DBUtil;

import java.util.List;

public class RoomDAO {
    /**
     * 查询一共多少行数据（房间）
     *
     * @param sql
     * @param param
     * @return room总个数
     */
    public Integer getRoomCount(String sql,Object...param) {
        Integer count= DBUtil.getCount(sql,param);
        return count;
    }

    /**
     * 分页查询
     *
     * @param sql
     * @return 房间列表
     */
    public List<Room> getRoomByPage(String sql) {
        List<Room> roomList=DBUtil.getList(Room.class,sql);
        return  roomList;
    }

    /**
     * 通过房间ID找房间
     *
     * @param id
     * @return 一个房间对象
     */
    public Room getRoomById(Integer id) {
        String sql="select \n"+
                "roomId roomId,\n"+
                "roomNumber roomNumber,\n"+
                "roomSize roomSize,\n"+
                "roomPrice roomPrice,\n"+
                "roomState roomState,\n"+
                "roomType roomType \n"+
                "from \n"+
                "room where roomId=?";
        return DBUtil.getSingleObj(Room.class,sql,id);
    }

    /**
     * 更新房间room
     *
     * @param room
     * @return false更新失败，true更新成功
     */
    public boolean updateRoom(Room room) {
        String sql="update room set \n" +
                "roomNumber= ?,\n" +
                "roomSize= ?,\n" +
                "roomPrice= ?,\n" +
                "roomState= ?,\n" +
                "roomType= ?\n" +
                "where roomId= ? ";
        boolean flag=DBUtil.update(sql,room.getRoomNumber(),room.getRoomSize(),room.getRoomPrice(),room.getRoomState(),room.getRoomType(),room.getRoomId());
        return flag;
    }

    /**
     * 根据房间ID找到房间并更新房态
     * @param room
     * @param state
     * @return
     */
    public boolean updateRoomState(Room room,Integer state){
        String sql="update room set roomState=? where roomId=?";
        boolean flag=DBUtil.update(sql,state,room.getRoomId());
        return  flag;
    }

}
