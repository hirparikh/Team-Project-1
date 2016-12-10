/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

public interface IElement  
{
   State getState();
   void attach(IGameObserver obj);
   void detach(IGameObserver obj);
}