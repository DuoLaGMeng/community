package life.guangsi.community.dto;

import lombok.Data;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO<T> {
    private List<T> data;
    private boolean hasPrevious;
    private boolean hasNext;
    private boolean showFisrtPage;
    private boolean showEndPage;
    private Integer currentPage;//page
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.currentPage = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //上一页判断
        if (page == 1) {
            hasPrevious = false;
        } else {
            hasPrevious = true;
        }

        //下一页判断
        if (page == totalPage) {
            hasNext = false;
        } else {
            hasNext = true;
        }

        //第一页判断
        if (pages.contains(1)) {
            showFisrtPage = false;
        } else {
            showFisrtPage = true;
        }

        //最后一页判断
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
