package game;

import java.util.List;
import java.util.Map;

public class BattleIdSelect {
	
/**
	CREATE TABLE `ROLE_BATTLE_BASE` (                                       
            `roleid` bigint(15) NOT NULL,
            `userid` text,                                           
            `rolename` text,                                                      
            `level` int(3) DEFAULT NULL,                                          
            `maxbattleId` int(4) DEFAULT NULL COMMENT '开启的最大battleId',  
            PRIMARY KEY (`roleid`)                                                
          ) ENGINE=InnoDB DEFAULT CHARSET=utf8 
*/
	
	/**
	 *  查询导出  csv 文件
select roleid,userid,rolename,`level`,maxbattleId from ROLE_BATTLE_BASE into outfile 'e://output20161209.csv' fields terminated by ',' optionally enclosed by ''lines terminated by '\r\n';

select roleid,userid,rolename, max(level) as level,maxbattleId from ROLE_BATTLE_BASE group by userid into outfile 'e://output20161209.csv' fields terminated by ',' optionally enclosed by ''lines terminated by '\r\n';

select roleid,rolename,`level`,maxbattleId from ROLE_BATTLE_BASE into outfile 'e://output.csv' fields terminated by ',' optionally enclosed by ''lines terminated by '\r\n';
	*/
	
	/**
	 * DBDispatcher 添加方法
	 *  
	public void test(){
		String sql = "select t1.roleid as roleid, userid, level, rolename, battle_info from UMS_ROLE_BASE t1,  UMS_ROLE_DETAIL_B t2 where t1.roleid = t2.roleid";
		String insert = "insert ROLE_BATTLE_BASE(roleid, userid,rolename,level , maxbattleId) values(?,?,?,?,?)";
		try{
			List<Map<String, Object>> result = m_runner.query(dbConn.getConn(), sql, new MapListHandler());
			Object[][] rankDatas = new Object[result.size()][5];
			int index = 0;
			for (Map<String, Object> mapResult : result) {
				int roleid = (int) mapResult.get("roleid");
				String userid = (String) mapResult.get("userid");
				int level = (int) mapResult.get("level");
				String rolename = (String) mapResult.get("rolename");
//				int maxNorPassBattle = 0;
				int maxNorEnterBattle = 0;
				if(mapResult.get("battle_info") != null){
					PBBattleInfo info = PBBattleInfo.parseFrom(((byte[]) mapResult.get("battle_info")));
//					maxNorPassBattle = info.getMaxNormalPassBattle();
					maxNorEnterBattle = info.getMaxNorEnterBattle();
				}
				rankDatas[index][0] = roleid;
				rankDatas[index][1] = userid;
				rankDatas[index][2] = rolename;
				rankDatas[index][3] = level;
				rankDatas[index][4] = maxNorEnterBattle;
				index++;
//				System.err.println(String.format("%s , %s , %s , %s ", roleid , rolename, level , maxNorPassBattle ));
			}
			System.err.println("~~~~~~~~~~~~~~~~~~ 玩家总数量: "+result.size());
			try{
				dbConn.getConn().setAutoCommit(false);
				m_runner.batch(dbConn.getConn(), insert, rankDatas);
				dbConn.getConn().commit();
				
			}catch(Exception e){
				s_logger.error("exe update ROLE_BATTLE_BASE exception:", e);
			}
		}catch(Exception e){
			s_logger.error("insert GMS_UDP STATICS exception:", e);
		}
	}
	*/
}
