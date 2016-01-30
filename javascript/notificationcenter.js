var HY = HY || {};
HY.NotificationCenter = function(){
	this._observers = {};
}
HY.NotificationCenter.prototype.addObserver = function(target,fun,name,sender){
	if(!this._observers[name]) {
		this._observers[name] = [];
	}
	this._observers[name].push({target:target,fun:fun,sender:sender});
}
HY.NotificationCenter.prototype.removeObserver = function(target,fun,name,sender){
	if(this._observers[name]){
		for(var i=this._observers[name].length-1;i>=0;--i){
			var observer = this._observers[name][i];
			if(observer.target == target && observer.fun == fun && observer.sender == sender){
				this._observers[name].splice(i,1);
			}
		}
	}
}
HY.NotificationCenter.prototype.postNotification = function(name, sender, param){
	if(this._observers[name]){
		var observers = this._observers[name];
		var len = observers.length;
		var observer;
		for(var i=0;i<len;++i){
			try{
				observer = observers[i];
				if(!observer.sender || observer.sender == sender){
					observer.fun.call(observer.target, sender, param);
				}
			}catch(err){ }
		}
	}
}