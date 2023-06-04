import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // select * from Items where item_name like '{itemName}'
    List<Item> findByItemNameLike(String itemName);

    // select item_id from Items
    // where item_name = '{itemName}'
    // and price = {price} limit 1
    boolean existsByItemNameAndPrice(String itemName, Long price);

    // select count(*) from Items where item_name like '%{itemName}%'
    int countByItemNameLike(String itemName);

    // delete from Items where price between {price1} and {price2}
    void deleteByPriceBetween(long price1, long price2);

    @Question
    List<Item> findByPriceIn(Collection<Long> prices);

    // TODO #1: @Query
    @Query("select i from Item i where i.price > ?1")
    List<Item> getItemsHavingPriceAtLeast(long price);

    // TODO #2: @Query with native query
    @Query(value = "select * from Items where price > ?1", nativeQuery = true)
    List<Item> getItemsHavingPriceAtLeast2(long price);

    // TODO #3: @Modifying
    @Modifying
    @Query("update Item i set i.itemName = :itemName where i.itemId = :itemId")
    int updateItemName(@Param("itemId") Long itemId, @Param("itemName")String itemName);

}