package com.geekmake.plugin.test;

import java.util.List;

/**
 * @author pez1420@gmail.com
 * @version $Id: TestClass.java v 0.1 2020/3/30 7:04 下午 pez1420 Exp $$
 */
public interface TestClass {

    int getResult(InputDO inputDO);

    int getResult(List<InputDO> inputDOs, String bizCode, Integer bizType);

    int getResult(InputDO[] inputDOs, String bizCode, Integer bizType);

    int getResult(Long[] ids, String bizCode, Integer bizType);

    int getResult(long it, Long id, String bizCode, Integer bizType);

}
