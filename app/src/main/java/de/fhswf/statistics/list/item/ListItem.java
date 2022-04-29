package de.fhswf.statistics.list.item;

/**
 * Interface für List-Items, welche durch den {@link de.fhswf.statistics.list.Adapter.ListAdapter}
 * verwaltet werden.
 */
public interface ListItem {

    /**
     * @return Integer welcher einen Typen darstellt.
     */
    int getType();

}
