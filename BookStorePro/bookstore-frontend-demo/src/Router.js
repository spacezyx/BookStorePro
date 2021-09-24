import React from 'react';
import { Router, Route, Switch, Redirect} from 'react-router-dom';
import PrivateRoute from './PrivateRoute'
import LoginRoute from  './LoginRoute'
import HomeView from "./view/HomeView";
import HomeLayout from "./view/HomeLayout"
import LoginView from './view/LoginView'
import {history} from "./utils/history";
import CartView from "./view/CartView";
import OrderView from "./view/OrderView";
import Home from "./view/OrderView";
import OrderHome from "./view/OrderView";
import OHome from "./view/OrderView";


class BasicRoute extends React.Component{

    constructor(props) {
        super(props);

        history.listen((location, action) => {
            // clear alert on location change
            console.log(location,action);
        });
    }

    render(){
        return(
            <Router history={history}>
                <Switch>
                    <LoginRoute exact path="/login" component={LoginView} />
                    <PrivateRoute  path="/" component={HomeLayout} />
                    <Route path="/CartView" component={CartView} />
                    <Route path="/OHome" component={OHome} />
                    {/*<Route exact path="/CartView" component={CartView} />*/}
                    {/*<Route exact path="/OrderView" component={OrderView} />*/}
                    <Redirect from="/*" to="/home" />
                </Switch>

            </Router>
        )
    }


}

export default BasicRoute;