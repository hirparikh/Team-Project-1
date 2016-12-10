/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

import greenfoot.*;

public interface IGameSubject  
{
     public abstract void attach(IGameObserver obj);
     public abstract void detach(IGameObserver obj);
     public abstract void notifyObservers(Element element, boolean doChain);
}
