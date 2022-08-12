package com.codesoom.assignment.interfaces;

import java.util.Collection;

/**
 * {@link java.util.Collection}을 조작하는 클래스에 의해 구현되는 인터페이스 입니다
 * @param <In> 구현체에 전달하는 객체 타입 입니다
 * @param <Out> 구현체에서 반환하는 객체 타입 입니다
 */
public interface CRUDInterface<In , Out , K> {
    /**
     * @return Out들을 List로 반환합니다
     */
    Collection<Out> selectAll();

    /**
     * @param id Collection에서 가져올 id 입니다
     * @return id에 해당하는 Out타입의 객체를 반환합니다
     */
    Out selectById(K id);

    /**
     * @param in Collection에 넣을 객체입니다
     * @return Collection에 넣은 객체를 반환합니다
     */
    Out insert(In in);

    /**
     * @param id Collection에서 수정할 id입니다
     * @param in 수정할 정보가 있는 객체 입니다
     * @return 수정한 객체를 반환합니다
     */
    Out update(K id , In in);

    /**
     * @param id Collection에서 삭제할 id입니다
     * @return 삭제한 객체를 반환합니다
     */
    Out delete(K id);
}
