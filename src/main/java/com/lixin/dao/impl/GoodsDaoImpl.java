package com.lixin.dao.impl;

import com.lixin.common.utils.SystemUtils;
import com.lixin.common.utils.dbutils.DbUtils;
import com.lixin.common.utils.dbutils.GenerousBeanHandler;
import com.lixin.common.utils.dbutils.GenerousBeanListHandler;
import com.lixin.dao.GoodsDao;
import com.lixin.model.bo.GoodsBo;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lixin
 */
public class GoodsDaoImpl implements GoodsDao {

    private final QueryRunner runner = DbUtils.getRunner();

    private GoodsDaoImpl() {
    }

    public static GoodsDao getGoodsDao() {
        return GoodsDaoHolder.GOODS_DAO;
    }

    @Override
    public void insert(GoodsBo goodsBo) {
        String sql = "insert into goods(goods_id, seller_id, name, description, price, " +
                "create_time, remove) value (?,?,?,?,?,?,0);";
        try {
            runner.update(sql, goodsBo.getGoodsId(), goodsBo.getSellerId(), goodsBo.getName(),
                    goodsBo.getDescription(), goodsBo.getPrice(), LocalDateTime.now());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GoodsBo> list(int offset, int rows) {
        String sql = "select goods_id, seller_id, name, description, price, create_time " +
                "from goods where remove = 0 limit ?,?";
        List<GoodsBo> all = null;
        try {
            all = runner.query(sql, new GenerousBeanListHandler<>(GoodsBo.class), offset, rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public List<GoodsBo> findByUserId(String userId, int offset, int row) {
        String sql = "select goods_id, seller_id, name, description, price, create_time " +
                "from goods where remove = 0 and seller_id = ? limit ?,?";
        List<GoodsBo> list = null;
        try {
            list = runner.query(sql, new GenerousBeanListHandler<>(GoodsBo.class), userId, offset, row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer total() {
        String sql = "select count(*) from goods where remove = 0";
        try {
            Long rows = runner.query(sql, new ScalarHandler<>());
            return rows.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer total(String userId) {
        String sql = "select count(*) from goods where remove = 0 and seller_id = ?";
        try {
            Long rows = runner.query(sql, new ScalarHandler<>(), userId);
            return rows.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GoodsBo find(String goodsId) {
        String sql = "select goods_id, seller_id, name, description, price, create_time " +
                "from goods where remove = 0 and goods_id = ?";
        try {
            return runner.query(sql, new GenerousBeanHandler<>(GoodsBo.class), goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String goodsId) {
        String sql = "update goods set remove = 1 where goods_id = ?";
        try {
            runner.update(sql, goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String goodsId, Connection connection) {
        String sql = "update goods set remove = 1 where goods_id = ?";
        try {
            runner.update(connection, sql, goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GoodsBo goodsBo) {
        String goodsId = goodsBo.getGoodsId();
        GoodsBo old = find(goodsId);
        String sql = "update goods set name = ?, description = ? , price = ? where goods_id = ?";
        try {
            String newName = goodsBo.getName();
            String newDescription = goodsBo.getDescription();
            Integer newPrice = goodsBo.getPrice();
            String name = SystemUtils.isBlank(newName) ? newName : old.getName();
            String description = SystemUtils.isBlank(newDescription) ? newDescription : old.getDescription();
            Integer price = newPrice != null && newPrice > 0 ? newPrice : old.getPrice();
            runner.update(sql, name, description, price, goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Boolean isExist(String goodsId) {
        String sql = "select count(*) from goods where goods_id = ? and remove = 0";
        try {
            Long exist = runner.query(sql, new ScalarHandler<>(), goodsId);
            return exist > 0L;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static class GoodsDaoHolder {
        static final GoodsDao GOODS_DAO = new GoodsDaoImpl();
    }

}
