'use strict';
var React = require('react-native');

var {
    requireNativeComponent
} = React;

var iface = {
  name: 'ImageView',
  propTypes: {
    content: React.PropTypes.string
  },
};

// var Geolocation = React.createClass({
// 	watchID: (null: ?number),
// 	getInitialState: function(){
// 		return {
// 			initalPosition: 'unknown',
// 			lastPosition: 'unknown',
// 		}
// 	},

// 	componentDidMount: function(){
// 		navigator.geolocation.getCurrentPosition(
// 			(initalPosition) => this.setState(initalPosition),
// 			(error) => console.error(error)
// 		);
	
// 		this.watchID = navigator.geolocation.watchPosition(
// 			(lastPosition) => {this.setState({lastPosition})
// 			});
// 	},

// 	componentWillUnmount: function() {
//     	navigator.geolocation.clearWatch(this.watchID);
//  	},

//  	render: function(){
//  		console.log(this.state.initalPosition);
//  		return ;
//  	}

// });

module.exports = requireNativeComponent('BaiduMap', iface);