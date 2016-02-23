angular.module('username', [])
    .service('usernameService', function () {
        var userName = "Chody Fike";
        var iD = 0;
        var username = function () {
            return userName;
        }
        var setUsername = function (userNameToSetTo) {
            userName = userNameToSetTo;
        }
        var setID = function (newID) {
            iD = newID;
        }
        var id = function () {
            return iD;
        }
        return {
            username: username,
            setUsername: setUsername,
            id: id,
            setID: setID
        }
    })