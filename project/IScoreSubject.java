/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

public interface IScoreSubject  
{
    public abstract void attach(IScoreObserver obj);
	public abstract void detach(IScoreObserver obj);
	public abstract void notifyObservers(Score opponent);
	public abstract void setIsFinished(boolean isFinished);
}
